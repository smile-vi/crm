package com.t248.lhd.crm.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "orders")
public class OrdersSolr {
   // odr_id,odr_customer_no,odr_addr
    @Id
    @Field
    private Integer odr_id;
    @Field
    private String odr_customer_no;
    @Field
    private String odr_addr;

    public Integer getOdr_id() {
        return odr_id;
    }

    public void setOdr_id(Integer odr_id) {
        this.odr_id = odr_id;
    }

    public String getOdr_customer_no() {
        return odr_customer_no;
    }

    public void setOdr_customer_no(String odr_customer_no) {
        this.odr_customer_no = odr_customer_no;
    }

    public String getOdr_addr() {
        return odr_addr;
    }

    public void setOdr_addr(String odr_addr) {
        this.odr_addr = odr_addr;
    }
}
