package com.t248.lhd.crm.entity;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long usrId;
    @Column(name = "usr_name")
    private String usrName;
    @Column(name = "usr_password")
    private String usrPassword;
    /* @Column(name = "usr_role_id")
     private Long usrRoleId;*/
    @Column(name = "usr_flag")
    private Integer usrFlag;
    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "usr_role_id")
    private Role role;

    public Long getUsrId() {
        return usrId;
    }

    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    public Integer getUsrFlag() {
        return usrFlag;
    }

    public void setUsrFlag(Integer usrFlag) {
        this.usrFlag = usrFlag;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(){}

    public User(String usrName,String usrPassword){
        this.usrName = usrName;
        this.usrPassword=usrPassword;

    }
}

