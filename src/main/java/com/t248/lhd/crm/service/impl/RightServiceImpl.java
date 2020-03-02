package com.t248.lhd.crm.service.impl;

import com.t248.lhd.crm.entity.Right;
import com.t248.lhd.crm.repository.RightRepository;
import com.t248.lhd.crm.service.RightService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RightServiceImpl implements RightService {
    @Resource
    private RightRepository rightRepository;
    @Override
    public List<Right> rightsList(String rightType) {
        return rightRepository.findByRightType(rightType);
    }

    @Override
    public void addRole(Right right) {
        rightRepository.save(right);
    }
}
