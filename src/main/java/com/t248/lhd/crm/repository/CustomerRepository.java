package com.t248.lhd.crm.repository;

import com.t248.lhd.crm.entity.Customer;
import com.t248.lhd.crm.entity.Linkman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {
    Customer findByCustName(String custName);
    Customer findByCustNo(String custNo);

}
