package com.system.service.common.service.impl;

import com.system.service.common.domain.CodeVO;
import com.system.service.common.mapper.CodeMapper;
import com.system.service.common.service.ICodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 陈葳
 * @Date: 2018/12/21 15:32
 * @Description:
 */
@Service
public class CodeServiceImpl implements ICodeService {

    @Autowired
    private CodeMapper codeMapper;

    @Override
    public List<CodeVO> queryArea() {
        return codeMapper.selectArea();
    }

    @Override
    public List<CodeVO> queryRoad(String areaNo) {
        return codeMapper.selectRoad(areaNo);
    }

    @Override
    public List<CodeVO> queryStation(String roadNo) {
        return codeMapper.selectStation(roadNo);
    }

    @Override
    public List<CodeVO> queryStationByType(String roadNo,Integer type) {
        return codeMapper.selectStationByType(roadNo,type);
    }
}
