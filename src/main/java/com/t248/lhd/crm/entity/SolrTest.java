package com.t248.lhd.crm.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;


import java.io.Serializable;

@SolrDocument(collection = "app_info")
public class SolrTest implements Serializable {
    @Id
    @Field
    private Integer id;
    @Field
    private String softwareName;
    @Field
    private String APKName;
    public SolrTest(){

    }
    public String getAPKName() {
        return APKName;
    }

    public void setAPKName(String APKName) {
        this.APKName = APKName;
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
