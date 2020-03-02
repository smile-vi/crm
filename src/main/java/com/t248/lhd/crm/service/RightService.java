package com.t248.lhd.crm.service;

import com.t248.lhd.crm.entity.Right;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RightService {
    List<Right> rightsList(String rightType);
    void addRole(Right right);
}
