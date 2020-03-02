package com.t248.lhd.crm.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_role")
@JsonIgnoreProperties(value = {"hibernateLazyInitializar","handler"})
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "role_desc")
    private String roleDesc;
    @Column(name = "role_flag")
    private Integer roleFlag;
    @ManyToMany
    @JoinTable(name = "sys_role_right",joinColumns = @JoinColumn(name = "rf_role_id"),inverseJoinColumns = @JoinColumn(name = "rf_right_code"))
    @OrderBy(value = "right_code")
    @JsonIgnore
    private Set<Right> rights = new HashSet<Right>();
    @OneToMany(targetEntity = User.class,fetch = FetchType.EAGER,cascade=CascadeType.PERSIST,mappedBy = "role")
    @JsonIgnore
    private Set<User> users = new HashSet<User>();

    public Set<Right> getRights() {
        return rights;
    }

    public void setRights(Set<Right> rights) {
        this.rights = rights;
    }

    public Role(){
    }
    public Role(String roleName,String roleDesc,Integer roleFlag){
        this.roleName=roleName;
        this.roleDesc=roleDesc;
        this.roleFlag = roleFlag;
    }



    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public Integer getRoleFlag() {
        return roleFlag;
    }

    public void setRoleFlag(Integer roleFlag) {
        this.roleFlag = roleFlag;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


}
