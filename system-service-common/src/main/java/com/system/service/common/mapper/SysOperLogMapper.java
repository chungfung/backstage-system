package com.system.service.common.mapper;

import com.system.service.common.domain.SysOperLogVO;

import java.util.List;

/**
 * 操作日志 数据层
 *
 * @author system
 */
public interface SysOperLogMapper {
    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    void insertOperlog(SysOperLogVO operLog);

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    List<SysOperLogVO> selectOperLogList(SysOperLogVO operLog);

    /**
     * 批量删除系统操作日志
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    int deleteOperLogByIds(String[] ids);

    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    SysOperLogVO selectOperLogById(Long operId);

    /**
     * 清空操作日志
     */
    void cleanOperLog();
}
