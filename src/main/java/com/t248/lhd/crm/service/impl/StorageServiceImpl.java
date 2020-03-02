package com.t248.lhd.crm.service.impl;

import com.t248.lhd.crm.entity.Product;
import com.t248.lhd.crm.entity.Role;
import com.t248.lhd.crm.entity.Storage;
import com.t248.lhd.crm.repository.StorageRepository;
import com.t248.lhd.crm.service.StorageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {
    @Resource
    private StorageRepository storageRepository;
    @Override
    public Page<Storage> findStorage(String prodName, String stkWarehouse, Pageable pageable) {
        Specification specification=new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();

               if (prodName!=null&&!prodName.equals("")){
                   Join<Product, Storage> join = root.join("product", JoinType.LEFT);
                    predicates.add(criteriaBuilder.like(join.get("prodName"),"%"+prodName+"%"));
                }
                if (stkWarehouse!=null&&!stkWarehouse.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("stkWarehouse"),"%"+stkWarehouse+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return storageRepository.findAll(specification,pageable);
    }
}
