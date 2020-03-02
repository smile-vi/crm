package com.t248.lhd.crm.repository;

import com.t248.lhd.crm.entity.CusService;
import com.t248.lhd.crm.entity.Customer;
import com.t248.lhd.crm.entity.DomainVo;
import com.t248.lhd.crm.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CusServiceRepository extends JpaRepository<CusService,Long>, JpaSpecificationExecutor<CusService> {
    CusService findCusServiceBySvrId(long svrId);
    @Transactional
    @Modifying
    @Query(value = "select count(c.svr_type) as serviceCount,c.svr_type FROM bas_dict b,cst_service  c\n" +
            "WHERE c.svr_type=b.dict_item\n" +
            "GROUP BY c.svr_type",nativeQuery = true)
    List<Object[]> getServiceList();
    @Query(value = "select new com.t248.lhd.crm.entity.DomainVo (c.svrType,count(c.svrType)) from CusService c " +
            "group by c.svrType")
    List<DomainVo> getService();


}
