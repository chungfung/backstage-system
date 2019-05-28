package com.system.service.common.mapper;

import com.system.service.common.domain.CodeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author 陈葳
 * @Date 2018/12/21 14:56
 * @Version 1.0
 */
public interface CodeMapper {

    /**
     * 获取区域编码
     * @return StationVO
     */
    List<CodeVO> selectArea();

    /**
     * 获取路段编码
     *
     * @param areaNo
     * @return StationVO
     */
    List<CodeVO> selectRoad(@Param("areaNo") String areaNo);

    /**
     * 获取站
     *
     * @param roadNo
     * @return List
     */
    List<CodeVO> selectStation(@Param("roadNo") String roadNo);

    /**
     * 获取站
     * @param roadNo
     * @param type
     * @return List
     */
    List<CodeVO> selectStationByType(@Param("roadNo") String roadNo, @Param("type") Integer type);
}
