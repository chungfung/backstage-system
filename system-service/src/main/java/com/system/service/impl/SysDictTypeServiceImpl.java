package com.system.service.impl;

import com.system.common.constant.UserConstants;
import com.system.common.page.PageBean;
import com.system.common.page.PageParam;
import com.system.common.text.Convert;
import com.system.common.utils.StringUtils;
import com.system.facade.exception.dict.DictException;
import com.system.facade.service.ISysDictTypeService;
import com.system.facade.vo.SysDictTypeVO;
import com.system.service.mapper.SysDictDataMapper;
import com.system.service.mapper.SysDictTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典 业务层处理
 *
 * @author system
 */
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService {

    @Autowired
    private SysDictTypeMapper dictTypeMapper;

    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 根据条件分页查询字典类型
     *
     * @param sysDictTypeVO 字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public PageBean<SysDictTypeVO> selectDictTypeList(SysDictTypeVO sysDictTypeVO, PageParam pageParam){
        pageParam.startPage();

        List<SysDictTypeVO> list = dictTypeMapper.selectDictTypeList(sysDictTypeVO);
        PageBean<SysDictTypeVO> pageBean = new PageBean<>(list);

        return pageBean;
    }

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictTypeVO> selectDictTypeAll() {
        return dictTypeMapper.selectDictTypeAll();
    }

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    @Override
    public SysDictTypeVO selectDictTypeById(Long dictId) {
        return dictTypeMapper.selectDictTypeById(dictId);
    }

    /**
     * 通过字典ID删除字典信息
     *
     * @param dictId 字典ID
     * @return 结果
     */
    @Override
    public int deleteDictTypeById(Long dictId) {
        return dictTypeMapper.deleteDictTypeById(dictId);
    }

    /**
     * 批量删除字典类型
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    @Override
    public int deleteDictTypeByIds(String ids){
        Long[] dictIds = Convert.toLongArray(ids);
        for (Long dictId : dictIds) {
            SysDictTypeVO dictType = selectDictTypeById(dictId);
            if (dictDataMapper.countDictDataByType(dictType.getDictType()) > 0) {
                throw DictException.DICT_USED_EXCEPTION;
            }
        }

        return dictTypeMapper.deleteDictTypeByIds(dictIds);
    }

    /**
     * 新增保存字典类型信息
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    public int insertDictType(SysDictTypeVO dictType) {
        String uniqueResult = checkDictTypeUnique(dictType);
        if(uniqueResult.equals(UserConstants.DICT_TYPE_NOT_UNIQUE)){
            throw DictException.DICT_EXISTS_EXCEPTION;
        }
        return dictTypeMapper.insertDictType(dictType);
    }

    /**
     * 修改保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    public int updateDictType(SysDictTypeVO dictType) {
        SysDictTypeVO oldDict = dictTypeMapper.selectDictTypeById(dictType.getId());
        dictDataMapper.updateDictDataType(oldDict.getDictType(), dictType.getDictType());
        return dictTypeMapper.updateDictType(dictType);
    }

    /**
     * 校验字典类型称是否唯一
     *
     * @param dict 字典类型
     * @return 结果
     */
    @Override
    public String checkDictTypeUnique(SysDictTypeVO dict) {
        Long dictId = StringUtils.isNull(dict.getId()) ? -1L : dict.getId();
        SysDictTypeVO dictType = dictTypeMapper.checkDictTypeUnique(dict.getDictType());
        if (StringUtils.isNotNull(dictType) && dictType.getId().longValue() != dictId.longValue()) {
            return UserConstants.DICT_TYPE_NOT_UNIQUE;
        }
        return UserConstants.DICT_TYPE_UNIQUE;
    }
}
