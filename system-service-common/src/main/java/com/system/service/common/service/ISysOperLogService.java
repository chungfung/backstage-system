package com.system.service.common.service;

import com.system.common.page.PageBean;
import com.system.common.page.PageParam;
import com.system.service.common.domain.SysOperLogVO;
import java.util.List;

/**
 * 操作日志 服务层
 *
 * @author ruoyi
 */
public interface ISysOperLogService {
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
     * @param pageParam 分页参数
     * @return 操作日志集合
     */
    PageBean<SysOperLogVO> selectOperLogList(SysOperLogVO operLog, PageParam pageParam);

    /**
     * 批量删除系统操作日志
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    String deleteOperLogByIds(String ids);

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
    String cleanOperLog();
}
