package com.t248.lhd.crm.service.impl;

import com.t248.lhd.crm.entity.CusService;
import com.t248.lhd.crm.entity.Customer;
import com.t248.lhd.crm.entity.Lost;
import com.t248.lhd.crm.entity.Orders;
import com.t248.lhd.crm.service.CustomerService;
import com.t248.lhd.crm.service.MailService;
import com.t248.lhd.crm.service.OrderService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

public class RamJob implements Job {
    @Resource
    private OrderService orderService;
    @Resource
    private CustomerService customerService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("每周六凌晨 02：00检查");
        List<Orders> ordersList=orderService.getLostCustomer();
       if (ordersList.size()!=0){
           for (Orders orders:
                   ordersList) {
               if (customerService.findLost(orders.getOdrCustomerNo())==null){
                   System.out.println("流失的客户编号:"+orders.getOdrCustomerNo());
                   Customer customer=customerService.findByCustNo(orders.getOdrCustomerNo());
                   Lost lost=new Lost();
                   lost.setLstCustManagerId(customer.getCustManagerId());
                   lost.setLstCustManagerName(customer.getCustManagerName());
                   lost.setLstCustNo(customer.getCustNo());
                   lost.setLstCustName(customer.getCustName());
                   lost.setLstLastOrderDate(orders.getOdrDate());
                   lost.setLstDelay("超过6个月未下单");
                   customerService.updatePoneLost(lost);
               }

           }
       }else{
           System.out.println("没有流失客户!");
       }

    }
}
