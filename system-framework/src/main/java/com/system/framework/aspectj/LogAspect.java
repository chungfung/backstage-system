package com.system.framework.aspectj;

import com.system.common.annotation.Log;
import com.system.common.enums.LogEnums;
import com.system.common.json.JSON;
import com.system.common.utils.IpUtils;
import com.system.common.utils.ServletUtils;
import com.system.common.utils.StringUtils;
import com.system.framework.manager.AsyncManager;
import com.system.framework.manager.factory.AsyncFactory;
import com.system.facade.vo.SysOperLogVO;
import com.system.facade.vo.UserVO;
import com.system.service.utils.ShiroUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 操作日志记录处理
 *
 * @author system
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 配置织入点
     */
    @Pointcut("@annotation(com.system.common.annotation.Log)")
    public void logPointCut() {
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()")
    public void doAfterReturning(JoinPoint joinPoint) {
        handleLog(joinPoint, null);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e);
    }

    /**
     * 日志处理
     *
     * @param joinPoint 切点
     * @param e         异常信息
     */
    protected void handleLog(final JoinPoint joinPoint, final Exception e) {
        try {
            // 获得注解
            Log controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null) {
                return;
            }

            // 获取当前的用户
            UserVO currentUser = ShiroUtils.getUser();

            // *========数据库日志=========*//
            SysOperLogVO operLog = new SysOperLogVO();
            operLog.setStatus(LogEnums.Status.NORMAL.getCode());
            // 请求的地址
            String ip = IpUtils.getHostIp();
            operLog.setOperIp(ip);

            operLog.setOperUrl(ServletUtils.getRequest().getRequestURI());
            if (currentUser != null) {
                operLog.setOperName(currentUser.getLoginName());
            }

            if (e != null) {
                operLog.setStatus(LogEnums.Status.EXCEPTION.getCode());
                operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setMethod(className + "." + methodName + "()");
            // 处理设置注解上的参数
            getControllerMethodDescription(controllerLog, operLog);

            // 保存数据库
            AsyncManager.getInstance().executeScheduleTask(AsyncFactory.recordOper(operLog));
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log     日志
     * @param operLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(Log log, SysOperLogVO operLog) throws Exception {
        // 设置action动作
        operLog.setBusinessType(log.businessType().getCode());
        // 设置标题
        operLog.setTitle(log.title());
        // 设置操作人类别
        operLog.setOperatorType(log.operatorType().getCode());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData()) {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(operLog);
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param operLog 操作日志
     * @throws Exception 异常
     */
    private void setRequestValue(SysOperLogVO operLog) throws Exception {
        Map<String, String[]> map = ServletUtils.getRequest().getParameterMap();
        String params = JSON.marshal(map);
        operLog.setOperParam(StringUtils.substring(params, 0, 2000));
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private Log getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }
}
