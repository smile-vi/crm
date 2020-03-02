package com.t248.lhd.crm.entity;

import groovy.transform.ToString;



@ToString
public class DomainVo {
    private String domain;
    private Long visitCount;

    public DomainVo(String domain, Long visitCount) {
        this.domain = domain;
        this.visitCount = visitCount;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Long getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Long visitCount) {
        this.visitCount = visitCount;
    }
}