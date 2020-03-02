package com.t248.lhd.crm.service.impl;

import com.t248.lhd.crm.entity.Orders;
import com.t248.lhd.crm.repository.OrderRepository;
import com.t248.lhd.crm.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
     private OrderRepository orderRepository;
    @Override
    public List<Orders> getLostCustomer() {
        return orderRepository.getLostOrderCustomer();
    }

    @Override
    public List<Orders> findByOdrCustomerNo(String custNo) {
        return orderRepository.findByOdrCustomerNo(custNo);
    }
}
