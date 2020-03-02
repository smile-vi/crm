package com.t248.lhd.crm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "cst_service")
@JsonIgnoreProperties(value = {"hibenateLazyInitializer","handler"})
public class CusService implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "svr_id")
    private Long svrId;
    @Column(name = "svr_type")
    private String svrType;
    @Column(name = "svr_title")
    private String svrTitle;
    /*@Column(name = "svr_cust_no")
    private String svrCustNo;*/
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "svr_cust_no")
    private Customer customer;
    @Column(name = "svr_cust_name")
    private String svrCustName;
    @Column(name = "svr_status")
    private String svrStatus;
    @Column(name = "svr_request")
    private String svrRequest;
    @Column(name = "svr_create_id")
    private int svrCreateId;
    @Column(name = "svr_create_by")
    private String svrCreateBy;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name = "svr_create_date")
    private Date svrCreateDate;
    @Column(name = "svr_due_id")
    private Integer svrDueId;
    @Column(name = "svr_due_to")

    private String svrDueTo;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name = "svr_due_date")
    private Date svrDueDate;
    @Column(name = "svr_deal")
    private String svrDeal;
    @Column(name = "svr_deal_id")
    private Integer svrDealId;
    @Column(name = "svr_deal_by")
    private String svrDealBy;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name = "svr_deal_date")
    private Date svrDealDate;
    @Column(name = "svr_result")
    private String svrResult;
    @Column(name = "svr_satisfy")
    private Integer svrSatisfy;
    @Transient
    private Integer serviceCount;
    public Long getSvrId() {
        return svrId;
    }

    public void setSvrId(Long svrId) {
        this.svrId = svrId;
    }

    public String getSvrType() {
        return svrType;
    }

    public void setSvrType(String svrType) {
        this.svrType = svrType;
    }

    public String getSvrTitle() {
        return svrTitle;
    }

    public void setSvrTitle(String svrTitle) {
        this.svrTitle = svrTitle;
    }

   /* public String getSvrCustNo() {
        return svrCustNo;
    }

    public void setSvrCustNo(String svrCustNo) {
        this.svrCustNo = svrCustNo;
    }*/

    public String getSvrCustName() {
        return svrCustName;
    }

    public void setSvrCustName(String svrCustName) {
        this.svrCustName = svrCustName;
    }

    public String getSvrStatus() {
        return svrStatus;
    }

    public void setSvrStatus(String svrStatus) {
        this.svrStatus = svrStatus;
    }

    public String getSvrRequest() {
        return svrRequest;
    }

    public void setSvrRequest(String svrRequest) {
        this.svrRequest = svrRequest;
    }

    public int getSvrCreateId() {
        return svrCreateId;
    }

    public void setSvrCreateId(int svrCreateId) {
        this.svrCreateId = svrCreateId;
    }

    public String getSvrCreateBy() {
        return svrCreateBy;
    }

    public void setSvrCreateBy(String svrCreateBy) {
        this.svrCreateBy = svrCreateBy;
    }

    public Date getSvrCreateDate() {
        return svrCreateDate;
    }

    public void setSvrCreateDate(Date svrCreateDate) {
        this.svrCreateDate = svrCreateDate;
    }

    public Integer getSvrDueId() {
        return svrDueId;
    }

    public void setSvrDueId(Integer svrDueId) {
        this.svrDueId = svrDueId;
    }

    public String getSvrDueTo() {
        return svrDueTo;
    }

    public void setSvrDueTo(String svrDueTo) {
        this.svrDueTo = svrDueTo;
    }

    public Date getSvrDueDate() {
        return svrDueDate;
    }

    public void setSvrDueDate(Date svrDueDate) {
        this.svrDueDate = svrDueDate;
    }

    public String getSvrDeal() {
        return svrDeal;
    }

    public void setSvrDeal(String svrDeal) {
        this.svrDeal = svrDeal;
    }

    public Integer getSvrDealId() {
        return svrDealId;
    }

    public void setSvrDealId(Integer svrDealId) {
        this.svrDealId = svrDealId;
    }

    public String getSvrDealBy() {
        return svrDealBy;
    }

    public void setSvrDealBy(String svrDealBy) {
        this.svrDealBy = svrDealBy;
    }

    public Date getSvrDealDate() {
        return svrDealDate;
    }

    public void setSvrDealDate(Date svrDealDate) {
        this.svrDealDate = svrDealDate;
    }

    public String getSvrResult() {
        return svrResult;
    }

    public void setSvrResult(String svrResult) {
        this.svrResult = svrResult;
    }

    public Integer getSvrSatisfy() {
        return svrSatisfy;
    }

    public void setSvrSatisfy(Integer svrSatisfy) {
        this.svrSatisfy = svrSatisfy;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getCount() {
        return serviceCount;
    }

    public void setCount(Integer count) {
        this.serviceCount = count;
    }
}
