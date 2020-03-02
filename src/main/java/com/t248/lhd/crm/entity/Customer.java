package com.t248.lhd.crm.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cst_customer")
@JsonIgnoreProperties(value = {"hibenateLazyInitializer","handler"})
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_no")
    private String custNo;
    @Column(name = "cust_id")
    private Long custId;
    @Column(name = "cust_name")
    private String custName;
    @Column(name = "cust_region")
    private String custRegion;
    @Column(name = "cust_manager_id")
    private Integer custManagerId;
    @Column(name = "cust_manager_name")
    private String custManagerName;
    @Column(name = "cust_level")
    private Integer custLevel;
    @Column(name = "cust_level_label")
    private String custLevelLabel;
    @Column(name = "cust_satisfy")
    private Integer custSatisfy;
    @Column(name = "cust_credit")
    private Integer custCredit;
    @Column(name = "cust_addr")
    private String custAddr;
    @Column(name = "cust_zip")
    private String custZip;
    @Column(name = "cust_tel")
    private String custTel;
    @Column(name = "cust_fax")
    private String custFax;
    @Column(name = "cust_website")
    private String custWebsite;
    @Column(name = "cust_licence_no")
    private String custLicenceNo;
    @Column(name = "cust_chieftain")
    private String custChieftain;
    @Column(name = "cust_bankroll")
    private Integer custBankroll;
    @Column(name = "cust_status")
    private String custStatus;
    @Column(name = "cust_bank")
    private String custBank;
    @Column(name = "cust_bank_account")
    private String custBankAccount;
    @Column(name = "cust_turnover")
    private String custTurnover;

    @Column(name = "cust_national_tax_no")
    private String custNationalTaxNo;
    @OneToMany(targetEntity = CusService.class,fetch = FetchType.LAZY,cascade=CascadeType.PERSIST,mappedBy = "customer")
    @JsonIgnore
    private Set<CusService> cusServices = new HashSet<CusService>();
   /* @OneToMany(targetEntity = Linkman.class,fetch = FetchType.LAZY,cascade=CascadeType.PERSIST,mappedBy = "customer")
    @JsonIgnore
    private Set<Linkman> linkmen = new HashSet<Linkman>();*/
    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustRegion() {
        return custRegion;
    }

    public void setCustRegion(String custRegion) {
        this.custRegion = custRegion;
    }

    public Integer getCustManagerId() {
        return custManagerId;
    }

    public void setCustManagerId(Integer custManagerId) {
        this.custManagerId = custManagerId;
    }

    public String getCustManagerName() {
        return custManagerName;
    }

    public void setCustManagerName(String custManagerName) {
        this.custManagerName = custManagerName;
    }

    public Integer getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(Integer custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustLevelLabel() {
        return custLevelLabel;
    }

    public void setCustLevelLabel(String custLevelLabel) {
        this.custLevelLabel = custLevelLabel;
    }

    public Integer getCustSatisfy() {
        return custSatisfy;
    }

    public void setCustSatisfy(Integer custSatisfy) {
        this.custSatisfy = custSatisfy;
    }

    public Integer getCustCredit() {
        return custCredit;
    }

    public void setCustCredit(Integer custCredit) {
        this.custCredit = custCredit;
    }

    public String getCustAddr() {
        return custAddr;
    }

    public void setCustAddr(String custAddr) {
        this.custAddr = custAddr;
    }

    public String getCustZip() {
        return custZip;
    }

    public void setCustZip(String custZip) {
        this.custZip = custZip;
    }

    public String getCustTel() {
        return custTel;
    }

    public void setCustTel(String custTel) {
        this.custTel = custTel;
    }

    public String getCustFax() {
        return custFax;
    }

    public void setCustFax(String custFax) {
        this.custFax = custFax;
    }

    public String getCustWebsite() {
        return custWebsite;
    }

    public void setCustWebsite(String custWebsite) {
        this.custWebsite = custWebsite;
    }

    public String getCustLicenceNo() {
        return custLicenceNo;
    }

    public void setCustLicenceNo(String custLicenceNo) {
        this.custLicenceNo = custLicenceNo;
    }

    public String getCustChieftain() {
        return custChieftain;
    }

    public void setCustChieftain(String custChieftain) {
        this.custChieftain = custChieftain;
    }

    public Integer getCustBankroll() {
        return custBankroll;
    }

    public void setCustBankroll(Integer custBankroll) {
        this.custBankroll = custBankroll;
    }

    public String getCustStatus() {
        return custStatus;
    }

    public void setCustStatus(String custStatus) {
        this.custStatus = custStatus;
    }

    public String getCustBank() {
        return custBank;
    }

    public void setCustBank(String custBank) {
        this.custBank = custBank;
    }

    public String getCustBankAccount() {
        return custBankAccount;
    }

    public void setCustBankAccount(String custBankAccount) {
        this.custBankAccount = custBankAccount;
    }

    public String getCustNationalTaxNo() {
        return custNationalTaxNo;
    }

    public void setCustNationalTaxNo(String custNationalTaxNo) {
        this.custNationalTaxNo = custNationalTaxNo;
    }


    public Set<CusService> getCusServices() {
        return cusServices;
    }

    public void setCusServices(Set<CusService> cusServices) {
        this.cusServices = cusServices;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public String getCustTurnover() {
        return custTurnover;
    }

    public void setCustTurnover(String custTurnover) {
        this.custTurnover = custTurnover;
    }


}
