package com.t248.lhd.crm.service.impl;

import com.t248.lhd.crm.entity.Dict;
import com.t248.lhd.crm.entity.Role;
import com.t248.lhd.crm.repository.DictRepository;
import com.t248.lhd.crm.service.DictService;
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
public class DictServiceImpl implements DictService {
    @Resource
    private DictRepository dictRepository;
    @Override
    public List<Dict> findByDictType(String dictType) {
        return dictRepository.findByDictType(dictType);
    }

    @Override
    public Page<Dict> findDict(Long dictId, String dictItem,String dictType, Pageable pageable) {
        Specification specification=new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                if (dictId!=null&&dictId.longValue()!=0l){
                    predicates.add(criteriaBuilder.equal(root.get("dictId"),dictId));
                }
                if (dictItem!=null&&!dictItem.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("dictItem"),"%"+dictItem+"%"));
                }
                if (dictType!=null&&!dictType.equals("")){
                    predicates.add(criteriaBuilder.equal(root.get("dictType"),dictType));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return dictRepository.findAll(specification,pageable);
    }
}
