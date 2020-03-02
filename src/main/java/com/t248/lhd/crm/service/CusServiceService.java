package com.t248.lhd.crm.service;

import com.t248.lhd.crm.entity.CusService;
import com.t248.lhd.crm.entity.DomainVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CusServiceService {
    Page<CusService> findCusService(String svrTitle, String svrCustName, Pageable pageable);
    Page<CusService> findCusService(String svrTitle, String svrCustName,String svrType,String svrStatus, Pageable pageable);
    void saveService(CusService cusService);
    CusService findBySvrId(long svrId);
    List<Object[]> getServiceList();
    Page<Object[]> getHistoryInfo(Pageable pageable);

    List<DomainVo> getService();

}
