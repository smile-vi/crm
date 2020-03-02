package com.t248.lhd.crm.service;

import com.t248.lhd.crm.entity.Orders;

import java.util.List;

public interface OrderService {
    List<Orders> getLostCustomer();
    List<Orders> findByOdrCustomerNo(String custNo);
}
