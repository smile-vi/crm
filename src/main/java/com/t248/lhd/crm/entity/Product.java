package com.t248.lhd.crm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@JsonIgnoreProperties(value = {"hibenateLazyInitializer","handler"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private long prodId;
    @Column(name = "prod_name")
    private String prodName;
    @Column(name = "prod_type")
    private String prodType;
    @Column(name = "prod_batch")
    private String prodBatch;
    @Column(name = "prod_unit")
    private String prodUnit;
    @Column(name = "prod_price")
    private float prodPrice;
    @Column(name = "prod_memo")
    private String prodMemo;
    @OneToMany(targetEntity = Storage.class,fetch = FetchType.EAGER,cascade=CascadeType.PERSIST,mappedBy = "product")
    @JsonIgnore
    private Set<Storage> storages = new HashSet<Storage>();
    public long getProdId() {
        return prodId;
    }

    public void setProdId(long prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getProdBatch() {
        return prodBatch;
    }

    public void setProdBatch(String prodBatch) {
        this.prodBatch = prodBatch;
    }

    public String getProdUnit() {
        return prodUnit;
    }

    public void setProdUnit(String prodUnit) {
        this.prodUnit = prodUnit;
    }

    public float getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(float prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProdMemo() {
        return prodMemo;
    }

    public void setProdMemo(String prodMemo) {
        this.prodMemo = prodMemo;
    }

    public Set<Storage> getStorages() {
        return storages;
    }

    public void setStorages(Set<Storage> storages) {
        this.storages = storages;
    }
}
