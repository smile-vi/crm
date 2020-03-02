package com.t248.lhd.crm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cst_activity")
@JsonIgnoreProperties(value = {"hibenateLazyInitializer","handler"})
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "atv_id")
    private Long atvId;
    @Column(name = "atv_cust_no")
    private String atvCustNo;
    @Column(name = "atv_cust_name")
    private String atvCustName;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name = "atv_date")
    private Date atvDate;
    @Column(name = "atv_place")
    private String atvPlace;
    @Column(name = "atv_title")
    private String atvTitle;
    @Column(name = "atv_desc")
    private String atvDesc;
    @Column(name = "atv_meno")
    private String atvMeno;
    public Long getAtvId() {
        return atvId;
    }

    public void setAtvId(Long atvId) {
        this.atvId = atvId;
    }

    public String getAtvCustNo() {
        return atvCustNo;
    }

    public void setAtvCustNo(String atvCustNo) {
        this.atvCustNo = atvCustNo;
    }

    public String getAtvCustName() {
        return atvCustName;
    }

    public void setAtvCustName(String atvCustName) {
        this.atvCustName = atvCustName;
    }

    public Date getAtvDate() {
        return atvDate;
    }

    public void setAtvDate(Date atvDate) {
        this.atvDate = atvDate;
    }

    public String getAtvPlace() {
        return atvPlace;
    }

    public void setAtvPlace(String atvPlace) {
        this.atvPlace = atvPlace;
    }

    public String getAtvTitle() {
        return atvTitle;
    }

    public void setAtvTitle(String atvTitle) {
        this.atvTitle = atvTitle;
    }

    public String getAtvDesc() {
        return atvDesc;
    }

    public void setAtvDesc(String atvDesc) {
        this.atvDesc = atvDesc;
    }

    public String getAtvMeno() {
        return atvMeno;
    }

    public void setAtvMeno(String atvMeno) {
        this.atvMeno = atvMeno;
    }
}
