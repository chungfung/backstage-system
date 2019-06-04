package com.system.service.mapper;

import com.system.facade.vo.SysDictTypeVO;

import java.util.List;

/**
 * 字典表 数据层
 * @author user08
 */
public interface SysDictTypeMapper {
    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    List<SysDictTypeVO> selectDictTypeList(SysDictTypeVO dictType);

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    List<SysDictTypeVO> selectDictTypeAll();

    /**
     * 根据字典类型ID查询信息
     *
     * @param id 字典类型ID
     * @return 字典类型
     */
    SysDictTypeVO selectDictTypeById(Long id);

    /**
     * 通过字典ID删除字典信息
     *
     * @param id 字典ID
     * @return 结果
     */
    int deleteDictTypeById(Long id);

    /**
     * 批量删除字典类型
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    int deleteDictTypeByIds(Long[] ids);

    /**
     * 新增字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    int insertDictType(SysDictTypeVO dictType);

    /**
     * 修改字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    int updateDictType(SysDictTypeVO dictType);

    /**
     * 校验字典类型称是否唯一
     *
     * @param dictType 字典类型
     * @return 结果
     */
    SysDictTypeVO checkDictTypeUnique(String dictType);
}