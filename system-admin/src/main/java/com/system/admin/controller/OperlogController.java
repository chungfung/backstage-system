package com.system.admin.controller;

import com.system.common.annotation.Log;
import com.system.common.enums.LogEnums;
import com.system.common.page.PageBean;
import com.system.common.page.PageParam;
import com.system.framework.controller.BaseController;
import com.system.facade.vo.SysOperLogVO;
import com.system.facade.service.ISysOperLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 操作日志记录
 * @author system
 */
@Controller
@RequestMapping("/log")
public class OperlogController extends BaseController {

    private String LOG_LIST_VIEW = "log/listLog";
    private String LOG_DETAIL_VIEW = "log/logDetail";

    @Autowired
    private ISysOperLogService operLogService;

    @RequiresPermissions("log:listLog")
    @RequestMapping("listLog")
    @Log(title = "日志查询",businessType = LogEnums.BusinessType.OTHER,operatorType = LogEnums.OperatorType.OTHER)
    public String operlog(SysOperLogVO sysOperLogVO, PageParam pageParam,ModelMap modelMap) {
        PageBean<SysOperLogVO> pageBean = operLogService.selectOperLogList(sysOperLogVO,pageParam);
        modelMap.put("pageBean",pageBean);

        modelMap.put("businessTypes", LogEnums.BusinessType.values());
        modelMap.put("statuses", LogEnums.Status.values());
        return LOG_LIST_VIEW;
    }

    @RequiresPermissions("log:logDetail")
    @RequestMapping(value = "logDetail")
    @Log(title = "日志详情查询",businessType = LogEnums.BusinessType.OTHER,operatorType = LogEnums.OperatorType.OTHER)
    public String roleDetail(Long id, ModelMap modelMap) {
        if(id != null) {
            SysOperLogVO sysOperLogVO = operLogService.selectOperLogById(id);
            modelMap.put("detail",sysOperLogVO);
        }
        return LOG_DETAIL_VIEW;
    }

    @RequiresPermissions("log:delete")
    @RequestMapping(value = "delLog",method = RequestMethod.POST)
    @Log(title = "日志删除",businessType = LogEnums.BusinessType.DELETE,operatorType = LogEnums.OperatorType.MANAGE)
    public ModelAndView delete(@RequestParam String logIds) {
        return jsonView(operLogService.deleteOperLogByIds(logIds),"listLog",null,false);
    }

    @RequiresPermissions("log:deleteAll")
    @RequestMapping(value = "delAllLog",method = RequestMethod.POST)
    @Log(title = "日志清除",businessType = LogEnums.BusinessType.DELETE,operatorType = LogEnums.OperatorType.MANAGE)
    public ModelAndView deleteAll() {
        return jsonView(operLogService.cleanOperLog(),"listLog",null,false);
    }
}
