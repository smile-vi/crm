package com.t248.lhd.crm.service;

import com.t248.lhd.crm.entity.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomerService {
    Page<Customer> findCustomer(String custName, String custNo, String custRegion, String custManagerName, String custLevelLabel, Pageable pageable);
    List<Linkman> listMan(String lkmCustNo);
    Linkman findBylkmId(Long lkmId);
    void updateLinkman(Linkman linkman);
    void saveLinkman(Linkman linkman);
    void delLinkman(Long lkmId);
    List<Activity> findActivityByAtvCustNo(String atvCustNo);
    Page<Lost> findLost(String lstCustName, String lstCustManagerName, String lstStatus,Pageable pageable);
    List<Lost> findLstStatus();
    Lost findByLstId(Long lstId);
    void updatePoneLost(Lost lost);
    List<Customer> findByCustomerName();
    Customer findByCustName(String custNo);
    Customer findByCustNo(String custNo);
    List<Orders> findByOrders(String custNo);
    Lost findLost(String custNo);
    Page<Customer> findDo(String custName,Pageable pageable);
    XSSFWorkbook show();

}
