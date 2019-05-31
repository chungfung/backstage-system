package com.system.framework.manager.factory;

import com.system.common.utils.AddressUtils;
import com.system.common.utils.spring.SpringUtils;
import com.system.service.common.domain.SysOperLogVO;
import com.system.service.common.service.ISysOperLogService;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 * @author system
 */
public class AsyncFactory {

    /**
     * 操作日志记录
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final SysOperLogVO operLog) {
        return new TimerTask() {
            @Override
            public void run() {
            // 远程查询操作地点
            operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
            SpringUtils.getBean(ISysOperLogService.class).insertOperlog(operLog);
            }
        };
    }
}