package com.system.admin.controller;

import com.system.common.page.PageBean;
import com.system.common.page.PageParam;
import com.system.framework.controller.BaseController;
import com.system.service.common.domain.SysDictTypeVO;
import com.system.service.common.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 数据字典信息
 * @author system
 */
@Controller
@RequestMapping("/dict")
public class DictTypeController extends BaseController {

    public static final String TYPE_LIST_VIEW = "dict/listType";

    @Autowired
    private ISysDictTypeService dictTypeService;

    @RequestMapping(value = "list_type")
    public String userView(SysDictTypeVO sysDictTypeVO, PageParam pageParam, ModelMap modelMap) {
        PageBean<SysDictTypeVO> pageBean = dictTypeService.selectDictTypeList(sysDictTypeVO,pageParam);
        modelMap.put("pageBean",pageBean);
        return TYPE_LIST_VIEW;
    }
}
