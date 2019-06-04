package com.system.admin.controller;

import com.system.common.page.PageBean;
import com.system.common.page.PageParam;
import com.system.framework.controller.BaseController;
import com.system.facade.vo.SysDictDataVO;
import com.system.facade.service.ISysDictDataService;
import com.system.service.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 数据字典信息
 * @author system
 */
@Controller
@RequestMapping("/dict")
public class DictDataController extends BaseController {

    public static final String DATA_LIST_VIEW = "dict/listData";
    public static final String DATA_ADD_VIEW = "dict/dataAdd";
    public static final String DATA_EDIT_VIEW = "dict/dataEdit";

    @Autowired
    private ISysDictDataService dictDataService;

    @RequestMapping(value = "list_data")
    public String userView(SysDictDataVO sysDictDataVO, PageParam pageParam, ModelMap modelMap) {
        PageBean<SysDictDataVO> pageBean = dictDataService.selectDictDataList(sysDictDataVO,pageParam);
        modelMap.put("pageBean",pageBean);
        modelMap.put("dictType",sysDictDataVO.getDictType());
        return DATA_LIST_VIEW;
    }

    /**
     * 新增字典类型
     */
    @GetMapping("/add_data")
    public String add(String dictType, ModelMap mmap) {
        mmap.put("dictType", dictType);
        return DATA_ADD_VIEW;
    }

    /**
     * 新增保存字典类型
     */
    @PostMapping("/add_data")
    @ResponseBody
    public ModelAndView addSave(SysDictDataVO dict) {
        dict.setCreateUser(ShiroUtils.getUserName());
        return jsonView(dictDataService.insertDictData(dict)>0?null:"新增失败",true);
    }

    /**
     * 修改字典类型
     */
    @GetMapping("/edit_data")
    public String edit(Long dictCode, ModelMap mmap) {
        mmap.put("dict", dictDataService.selectDictDataById(dictCode));
        return DATA_EDIT_VIEW;
    }

    /**
     * 修改保存字典类型
     */
    @PostMapping("/edit_data")
    @ResponseBody
    public ModelAndView editSave(SysDictDataVO dict) {
        dict.setUpdateUser(ShiroUtils.getUserName());
        return jsonView(dictDataService.updateDictData(dict)>0?null:"修改失败",true);
    }

    @PostMapping("/remove_data")
    @ResponseBody
    public ModelAndView remove(String id) {
        return jsonView(dictDataService.deleteDictDataByIds(id)>0?null:"删除失败");
    }
}
