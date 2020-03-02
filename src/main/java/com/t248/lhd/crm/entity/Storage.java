package com.t248.lhd.crm.entity;

import javax.persistence.*;

@Entity
@Table(name = "storage")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stk_id")
    private long stkId;
   /* @Column(name = "stk_prod_id")
    private long stkProdId;*/
    @Column(name = "stk_warehouse")
    private String stkWarehouse;
    @Column(name = "stk_ware")
    private String stkWare;
    @Column(name = "stk_count")
    private int stkCount;
    @Column(name = "stk_memo")
    private String stkMemo;
    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "stk_prod_id")
    private Product product;
    public long getStkId() {
        return stkId;
    }

    public void setStkId(long stkId) {
        this.stkId = stkId;
    }


    public String getStkWarehouse() {
        return stkWarehouse;
    }

    public void setStkWarehouse(String stkWarehouse) {
        this.stkWarehouse = stkWarehouse;
    }

    public String getStkWare() {
        return stkWare;
    }

    public void setStkWare(String stkWare) {
        this.stkWare = stkWare;
    }

    public int getStkCount() {
        return stkCount;
    }

    public void setStkCount(int stkCount) {
        this.stkCount = stkCount;
    }

    public String getStkMemo() {
        return stkMemo;
    }

    public void setStkMemo(String stkMemo) {
        this.stkMemo = stkMemo;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
