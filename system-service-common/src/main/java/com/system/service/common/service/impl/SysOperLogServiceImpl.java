package com.system.service.common.service.impl;

import com.system.common.page.PageBean;
import com.system.common.page.PageParam;
import com.system.common.text.Convert;
import com.system.service.common.domain.SysOperLogVO;
import com.system.service.common.mapper.SysOperLogMapper;
import com.system.service.common.service.ISysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作日志 服务层处理
 *
 * @author system
 */
@Service
public class SysOperLogServiceImpl implements ISysOperLogService {

    @Autowired
    private SysOperLogMapper operLogMapper;

    /**
     * 新增操作日志
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(SysOperLogVO operLog) {
        operLogMapper.insertOperlog(operLog);
    }

    /**
     * 查询系统操作日志集合
     * @param operLog 操作日志对象
     * @param pageParam 分页参数
     * @return 操作日志集合
     */
    @Override
    public PageBean<SysOperLogVO> selectOperLogList(SysOperLogVO operLog, PageParam pageParam) {
        pageParam.startPage();
        List<SysOperLogVO> list = operLogMapper.selectOperLogList(operLog);

        PageBean<SysOperLogVO> pageBean = new PageBean<>(list);
        return pageBean;
    }

    /**
     * 批量删除系统操作日志
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public int deleteOperLogByIds(String ids) {
        return operLogMapper.deleteOperLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 查询操作日志详细
     * @param operId 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysOperLogVO selectOperLogById(Long operId) {
        return operLogMapper.selectOperLogById(operId);
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperLog() {
        operLogMapper.cleanOperLog();
    }
}
