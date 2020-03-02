package com.t248.lhd.crm.service;

import com.t248.lhd.crm.entity.Chance;
import com.t248.lhd.crm.entity.Plan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PlanService {
    List<Plan> findPaln(Long plaChcId);
    void delPlan(Long plaId);
    void savePlan(Plan plan);
    void updateSuccess(Chance chance);
}
