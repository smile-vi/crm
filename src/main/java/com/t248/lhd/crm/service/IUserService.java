package com.t248.lhd.crm.service;

import com.t248.lhd.crm.entity.Right;
import com.t248.lhd.crm.entity.Role;
import com.t248.lhd.crm.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IUserService {
    User login(String usrName,String usrPassword);
    void saveUser(User user);
    void deleteUser(Long usrId);
    User getUser(Long usrId);
    List<User> findUsers();
    Page<User> findUsers(String usrName, Long roleId, Pageable pageable);

    User getUser(String usrName);
    //获取所有角色
    List<Role> findAllRoles();
    //获取用户-->角色
    public Role getRoleByUser(User user);
    //保存角色
    public void saveRole(Role role);
    //获取所有权限
    public List<Right> findAllRights();
    //获取角色
    public List<Role> findRoles(String roleName);
    //获取用户-->角色-->权限
    public List<Right> findRightsByRole(Role role);

}
