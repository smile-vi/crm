package com.t248.lhd.crm.service.impl;

import com.t248.lhd.crm.entity.Chance;
import com.t248.lhd.crm.entity.Plan;
import com.t248.lhd.crm.repository.PlanRepository;
import com.t248.lhd.crm.service.PlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Resource
    private PlanRepository planRepository;
    @Override
    public List<Plan> findPaln(Long plaChcId) {
        return planRepository.findByPlaChcId(plaChcId);
    }

    @Override
    public void delPlan(Long plaId) {
        planRepository.deleteById(plaId);
    }

    @Override
    public void savePlan(Plan plan) {
        planRepository.save(plan);
    }

    @Override
    public void updateSuccess(Chance chance) {
        planRepository.updateSuccess(chance);
    }
}
