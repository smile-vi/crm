package com.t248.lhd.crm.repository;

import com.t248.lhd.crm.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.beans.Transient;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Long> {
    List<Orders> findByOdrCustomerNo(String odrCustomerNo);
    @Transient
    @Query(value = "SELECT `odr_customer_no`,odr_date,odr_id,odr_addr,odr_status\n" +
            "FROM `orders`\n" +
            "WHERE timestampdiff(month,odr_date,NOW())>6\n" +
            "GROUP BY `odr_customer_no`",nativeQuery = true)
    List<Orders> getLostOrderCustomer();
}
