package com.system.admin.controller;

import com.system.common.page.PageBean;
import com.system.common.page.PageParam;
import com.system.framework.controller.BaseController;
import com.system.service.common.domain.SysOperLogVO;
import com.system.service.common.service.ISysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 操作日志记录
 * @author system
 */
@Controller
@RequestMapping("/log")
public class OperlogController extends BaseController {

    private String LOG_LIST_VIEW = "log/listLog";

    @Autowired
    private ISysOperLogService operLogService;

    @RequestMapping("listLog")
    public String operlog(SysOperLogVO sysOperLogVO, PageParam pageParam,ModelMap modelMap) {
        PageBean<SysOperLogVO> pageBean = operLogService.selectOperLogList(sysOperLogVO,pageParam);
        modelMap.put("pageBean",pageBean);
        return LOG_LIST_VIEW;
    }

}
