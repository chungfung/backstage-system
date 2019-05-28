package com.system.service.common.service;


import com.system.service.common.domain.CodeVO;

import java.util.List;

/**
 * @Description
 * @Author 陈葳
 * @Date 2018/12/21 19:55
 * @Version 1.0
 */
public interface ICodeService {
    /**
     * 获取区域
     *
     * @return List
     */
    List<CodeVO> queryArea();
    /**
     * 获取路段
     * @return List
     */
    List<CodeVO> queryRoad(String areaNo);

    /**
     * 获取站
     *
     * @return List
     */
    List<CodeVO> queryStation(String roadNo);

    /**
     * 根据路段和类型获取站
     * @param roadNo
     * @param type
     * @return List
     */
    List<CodeVO> queryStationByType(String roadNo, Integer type);
}
