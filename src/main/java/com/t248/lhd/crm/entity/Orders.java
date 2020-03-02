package com.t248.lhd.crm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@JsonIgnoreProperties(value = {"hibenateLazyInitializer","handler"})
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "odr_id")
    private Long odrId;
    @Column(name = "odr_customer_no")
    private String odrCustomerNo;
    @Column(name = "odr_date")
    private Date odrDate;
    @Column(name = "odr_addr")
    private String odrAddr;
    @Column(name = "odr_status")
    private String odrStatus;
    @OneToMany(targetEntity = OrdersLine.class,fetch = FetchType.LAZY,cascade=CascadeType.ALL,mappedBy = "orders")
    @JsonIgnore
    private Set<OrdersLine> ordersLines = new HashSet<OrdersLine>();

    public Long getOdrId() {
        return odrId;
    }

    public void setOdrId(Long odrId) {
        this.odrId = odrId;
    }

    public String getOdrCustomerNo() {
        return odrCustomerNo;
    }

    public void setOdrCustomerNo(String odrCustomerNo) {
        this.odrCustomerNo = odrCustomerNo;
    }

    public Date getOdrDate() {
        return odrDate;
    }

    public void setOdrDate(Date odrDate) {
        this.odrDate = odrDate;
    }

    public String getOdrAddr() {
        return odrAddr;
    }

    public void setOdrAddr(String odrAddr) {
        this.odrAddr = odrAddr;
    }

    public String getOdrStatus() {
        return odrStatus;
    }

    public void setOdrStatus(String odrStatus) {
        this.odrStatus = odrStatus;
    }

    public Set<OrdersLine> getOrdersLines() {
        return ordersLines;
    }

    public void setOrdersLines(Set<OrdersLine> ordersLines) {
        this.ordersLines = ordersLines;
    }
}
