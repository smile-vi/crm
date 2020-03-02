package com.t248.lhd.crm.service.impl;

import com.t248.lhd.crm.entity.CusService;
import com.t248.lhd.crm.entity.DomainVo;
import com.t248.lhd.crm.entity.Role;
import com.t248.lhd.crm.repository.CusServiceRepository;
import com.t248.lhd.crm.service.CusServiceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CusServiceServiceImpl implements CusServiceService {
    @Resource
    private CusServiceRepository cusServiceRepository;
    @Override
    public Page<CusService> findCusService(String svrTitle, String svrCustName, Pageable pageable) {
        Specification specification=new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                System.out.println("svrTitle:"+svrTitle);
                System.out.println("svrCustName:"+svrCustName);
                if (svrTitle!=null&&!svrTitle.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("svrTitle"),"%"+svrTitle+"%"));
                }
                if (svrCustName!=null&&!svrCustName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("svrCustName"),"%"+svrCustName+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return cusServiceRepository.findAll(specification,pageable);
    }

    @Override
    public Page<CusService> findCusService(String svrTitle, String svrCustName, String svrType,String svrStatus, Pageable pageable) {
        Specification specification=new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                if (svrTitle!=null&&!svrTitle.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("svrTitle"),"%"+svrTitle+"%"));
                }
                if (svrCustName!=null&&!svrCustName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("svrCustName"),"%"+svrCustName+"%"));
                }
                if (svrType!=null&&!svrType.equals("")){
                    predicates.add(criteriaBuilder.equal(root.get("svrType"),svrType));
                }
                if (svrStatus!=null&&!svrStatus.equals("")){
                    predicates.add(criteriaBuilder.equal(root.get("svrStatus"),svrStatus));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return cusServiceRepository.findAll(specification,pageable);
    }

    @Override
    public void saveService(CusService cusService) {
        cusServiceRepository.save(cusService);
    }

    @Override
    public CusService findBySvrId(long svrId) {
        return cusServiceRepository.findCusServiceBySvrId(svrId);
    }

    @Override
    public List<Object[]> getServiceList() {
        return cusServiceRepository.getServiceList();
    }

    @Override
    public Page<Object[]> getHistoryInfo(
            Pageable pageable) {

        StringBuffer dataBuffer = new StringBuffer(
                "select count(c.svr_type) as count,c.svr_type from bas_dict b,cst_service  c WHERE 1 = 1 and c.svr_type=b.dict_item\n" +
                        "GROUP BY c.svr_type");
        StringBuffer countBuffer = new StringBuffer(
                "select count(*) from bas_dict WHERE dict_type='服务类型'");

        StringBuffer paramBuffer = new StringBuffer();


        StringBuffer orderBuffer = new StringBuffer(
                " ORDER BY b.dict_id asc");

        String dataSql = (dataBuffer.append(paramBuffer).append(orderBuffer))
                .toString();
        String countSql = (countBuffer.append(paramBuffer)).toString();

        System.out.println("{} dataSql= " + dataSql);
        System.out.println("{} countSql= " + countSql);

        Query dataQuery = entityManager.createNativeQuery(dataSql.toString());
        Query countQuery = entityManager.createNativeQuery(countSql.toString());

        dataQuery.setFirstResult((int)pageable.getOffset());
        dataQuery.setMaxResults(pageable.getPageSize());
        /*BigDecimal count = (BigDecimal) countQuery.getSingleResult();*/
        String aa=countQuery.getSingleResult().toString();

        int total = Integer.valueOf(aa);
        List<Object[]> content = total > pageable.getOffset() ? dataQuery
                .getResultList() : Collections.emptyList();
        return new PageImpl<>(content, pageable, total);
        // return null;
    }

    @Override
    public List<DomainVo> getService() {
        return cusServiceRepository.getService();
    }

    @PersistenceContext
    private EntityManager entityManager;




}
