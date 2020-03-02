package com.t248.lhd.crm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
@Entity
@Table(name = "orders_line")
@JsonIgnoreProperties(value = {"hibenateLazyInitializer","handler"})
public class OrdersLine {
    @Id
    @GeneratedValue
    @Column(name = "odd_id")
    private Long oddId;                 //明细编号
    /*@Column(name = "odd_order_id")
    private Long oddOrderId;            //订单编号*/
  /*@Column(name = "odd_prod_id")
  private Long oddProdId;             //商品编号*/
    @Column(name = "odd_count")
    private Long oddCount;              //数量
    @Column(name = "odd_unit")
    private String oddUnit;             //单位
    @Column(name = "odd_price")
    private double oddPrice;            //金额

    @ManyToOne(targetEntity = Orders.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "odd_order_id")
    private Orders orders;

    @ManyToOne(targetEntity = Product.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "odd_prod_id")
    private Product product;

    public Long getOddId() {
        return oddId;
    }

    public void setOddId(Long oddId) {
        this.oddId = oddId;
    }

    public Long getOddCount() {
        return oddCount;
    }

    public void setOddCount(Long oddCount) {
        this.oddCount = oddCount;
    }

    public String getOddUnit() {
        return oddUnit;
    }

    public void setOddUnit(String oddUnit) {
        this.oddUnit = oddUnit;
    }

    public double getOddPrice() {
        return oddPrice;
    }

    public void setOddPrice(double oddPrice) {
        this.oddPrice = oddPrice;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
