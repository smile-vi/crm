package com.t248.lhd.crm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "sal_chance")
@JsonIgnoreProperties(value = {"hibenateLazyInitializer","handler"})
public class Chance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chc_id")
    private Long chcId;
    @Column(name = "chc_source")
    private String chcSource;
    @Column(name = "chc_cust_name")
    private String chcCustName;
    @Column(name = "chc_title")
    private String chcTitle;
    @Column(name = "chc_rate")
    private Integer chcRate;
    @Column(name = "chc_linkman")
    private String chcLinkman;
    @Column(name = "chc_tel")
    private String chcTel;
    @Column(name = "chc_desc")
    private String chcDesc;
    @Column(name = "chc_create_id")
    private Long chcCreateId;
    @Column(name = "chc_create_by")
    private String chcCreateBy;
    @Column(name = "chc_create_date")
    private Date chcCreateDate;
    @Column(name = "chc_due_id")
    private Integer chcDueId;
    @Column(name = "chc_due_to")
    private String chcDueTo;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "chc_due_date")
    private Date chcDueDate;
    @Column(name = "chc_status")
    private String chcStatus;
    //客户等级
    @Column(name = "chc_cust_level_label")
    private String custLevelLabel;
    //客户地区
    @Column(name = "chc_cust_region")
    private String custRegion;
    public Long getChcId() {
        return chcId;
    }

    public void setChcId(Long chcId) {
        this.chcId = chcId;
    }

    public String getChcSource() {
        return chcSource;
    }

    public void setChcSource(String chcSource) {
        this.chcSource = chcSource;
    }

    public String getChcCustName() {
        return chcCustName;
    }

    public void setChcCustName(String chcCustName) {
        this.chcCustName = chcCustName;
    }

    public String getChcTitle() {
        return chcTitle;
    }

    public void setChcTitle(String chcTitle) {
        this.chcTitle = chcTitle;
    }

    public Integer getChcRate() {
        return chcRate;
    }

    public void setChcRate(Integer chcRate) {
        this.chcRate = chcRate;
    }

    public String getChcLinkman() {
        return chcLinkman;
    }

    public void setChcLinkman(String chcLinkman) {
        this.chcLinkman = chcLinkman;
    }

    public String getChcTel() {
        return chcTel;
    }

    public void setChcTel(String chcTel) {
        this.chcTel = chcTel;
    }

    public String getChcDesc() {
        return chcDesc;
    }

    public void setChcDesc(String chcDesc) {
        this.chcDesc = chcDesc;
    }

    public Long getChcCreateId() {
        return chcCreateId;
    }

    public void setChcCreateId(Long chcCreateId) {
        this.chcCreateId = chcCreateId;
    }

    public String getChcCreateBy() {
        return chcCreateBy;
    }

    public void setChcCreateBy(String chcCreateBy) {
        this.chcCreateBy = chcCreateBy;
    }

    public Date getChcCreateDate() {
        return chcCreateDate;
    }

    public void setChcCreateDate(Date chcCreateDate) {
        this.chcCreateDate = chcCreateDate;
    }

    public Integer getChcDueId() {
        return chcDueId;
    }

    public void setChcDueId(Integer chcDueId) {
        this.chcDueId = chcDueId;
    }

    public String getChcDueTo() {
        return chcDueTo;
    }

    public void setChcDueTo(String chcDueTo) {
        this.chcDueTo = chcDueTo;
    }

    public Date getChcDueDate() {
        return chcDueDate;
    }

    public void setChcDueDate(Date chcDueDate) {
        this.chcDueDate = chcDueDate;
    }

    public String getChcStatus() {
        return chcStatus;
    }

    public void setChcStatus(String chcStatus) {
        this.chcStatus = chcStatus;
    }

    public String getCustLevelLabel() {
        return custLevelLabel;
    }

    public void setCustLevelLabel(String custLevelLabel) {
        this.custLevelLabel = custLevelLabel;
    }

    public String getCustRegion() {
        return custRegion;
    }

    public void setCustRegion(String custRegion) {
        this.custRegion = custRegion;
    }
}
