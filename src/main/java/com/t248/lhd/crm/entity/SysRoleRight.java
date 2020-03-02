package com.t248.lhd.crm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "sys_role_right")
@JsonIgnoreProperties(value = {"hibernateLazyInitializar","handler"})
public class SysRoleRight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rf_id")
    private Long rfId;
    @Column(name = "rf_role_id")
    private Long rf_role_id;
    @Column(name = "rf_right_code")
    private String rf_right_code;

    public Long getRfId() {
        return rfId;
    }

    public void setRfId(Long rfId) {
        this.rfId = rfId;
    }

    public Long getRf_role_id() {
        return rf_role_id;
    }

    public void setRf_role_id(Long rf_role_id) {
        this.rf_role_id = rf_role_id;
    }

    public String getRf_right_code() {
        return rf_right_code;
    }

    public void setRf_right_code(String rf_right_code) {
        this.rf_right_code = rf_right_code;
    }
}
