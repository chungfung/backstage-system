package com.system.admin.controller;

import com.system.common.page.PageBean;
import com.system.common.page.PageParam;
import com.system.framework.controller.BaseController;
import com.system.service.common.domain.SysDictDataVO;
import com.system.service.common.service.ISysDictDataService;
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
public class DictDataController extends BaseController {

    public static final String DATA_LIST_VIEW = "dict/listData";

    @Autowired
    private ISysDictDataService dictDataService;

    @RequestMapping(value = "list_data")
    public String userView(SysDictDataVO sysDictDataVO, PageParam pageParam, ModelMap modelMap) {
        PageBean<SysDictDataVO> pageBean = dictDataService.selectDictDataList(sysDictDataVO,pageParam);
        modelMap.put("pageBean",pageBean);
        return DATA_LIST_VIEW;
    }
}
