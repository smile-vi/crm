package com.t248.lhd.crm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cst_lost")
@JsonIgnoreProperties(value = {"hibenateLazyInitializer","handler"})
public class Lost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lst_id")
    private long lstId;
    @Column(name = "lst_cust_no")
    private String lstCustNo;
    @Column(name = "lst_cust_name")
    private String lstCustName;
    @Column(name = "lst_cust_manager_id")
    private Integer lstCustManagerId;
    @Column(name = "lst_cust_manager_name")
    private String lstCustManagerName;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "lst_last_order_date")
    private Date lstLastOrderDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "lst_lost_date")
    private Date lstLostDate;
    @Column(name = "lst_delay")
    private String lstDelay;
    @Column(name = "lst_reason")
    private String lstReason;
    @Column(name = "lst_status")
    private String lstStatus;

    public long getLstId() {
        return lstId;
    }

    public void setLstId(long lstId) {
        this.lstId = lstId;
    }

    public String getLstCustNo() {
        return lstCustNo;
    }

    public void setLstCustNo(String lstCustNo) {
        this.lstCustNo = lstCustNo;
    }

    public String getLstCustName() {
        return lstCustName;
    }

    public void setLstCustName(String lstCustName) {
        this.lstCustName = lstCustName;
    }

    public Integer getLstCustManagerId() {
        return lstCustManagerId;
    }

    public void setLstCustManagerId(Integer lstCustManagerId) {
        this.lstCustManagerId = lstCustManagerId;
    }

    public String getLstCustManagerName() {
        return lstCustManagerName;
    }

    public void setLstCustManagerName(String lstCustManagerName) {
        this.lstCustManagerName = lstCustManagerName;
    }

    public Date getLstLastOrderDate() {
        return lstLastOrderDate;
    }

    public void setLstLastOrderDate(Date lstLastOrderDate) {
        this.lstLastOrderDate = lstLastOrderDate;
    }

    public Date getLstLostDate() {
        return lstLostDate;
    }

    public void setLstLostDate(Date lstLostDate) {
        this.lstLostDate = lstLostDate;
    }

    public String getLstDelay() {
        return lstDelay;
    }

    public void setLstDelay(String lstDelay) {
        this.lstDelay = lstDelay;
    }

    public String getLstReason() {
        return lstReason;
    }

    public void setLstReason(String lstReason) {
        this.lstReason = lstReason;
    }

    public String getLstStatus() {
        return lstStatus;
    }

    public void setLstStatus(String lstStatus) {
        this.lstStatus = lstStatus;
    }
}
