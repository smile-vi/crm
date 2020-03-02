package com.t248.lhd.crm.service.impl;
import com.t248.lhd.crm.entity.Chance;
import com.t248.lhd.crm.repository.ChanceRepository;
import com.t248.lhd.crm.service.ChanceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChanceServiceImpl implements ChanceService {
    @Resource
    private ChanceRepository chanceRepository;
    @Override
    public Page<Chance> findChance(String chcCustName, String chcTitle, Pageable pageable) {
        Specification specification=new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();

                if (chcCustName!=null&&!chcCustName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("chcCustName"),"%"+chcCustName+"%"));
                }
                if (chcTitle!=null&&!chcTitle.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("chcTitle"),"%"+chcTitle+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return chanceRepository.findAll(specification,pageable);
    }

    @Override
    public Page<Chance> findChance(String chcCustName, String chcTitle, String chcLinkman, Pageable pageable) {
        Specification specification=new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();

                if (chcCustName!=null&&!chcCustName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("chcCustName"),"%"+chcCustName+"%"));
                }
                if (chcTitle!=null&&!chcTitle.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("chcTitle"),"%"+chcTitle+"%"));
                }
                if (chcLinkman!=null&&!chcLinkman.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("chcLinkman"),"%"+chcLinkman+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return chanceRepository.findAll(specification,pageable);
    }

    @Override
    public void saveChance(Chance chance) {
        chanceRepository.save(chance);
    }

    @Override
    public Chance getChance(Long chcId) {
        return chanceRepository.getByChcId(chcId);
    }

    @Override
    public void deleteChance(Long chcId) {
        chanceRepository.deleteById(chcId);
    }

    @Override
    public void updateStatus(Chance chance) {
        chanceRepository.updateStatus(chance);
    }
}
