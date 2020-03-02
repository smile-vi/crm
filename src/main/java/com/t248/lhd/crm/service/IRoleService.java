package com.t248.lhd.crm.service;

import com.t248.lhd.crm.entity.Role;
import com.t248.lhd.crm.entity.SysRoleRight;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IRoleService {
    List<Role> findAllRoles();
    void addRole(Role role);
    Role getRole(Long roleId);
    void deleteRole(Long roleId);
    Role findByRoleName(String roleName);
    void addSysRightRole(String rf_right_code ,Long rf_role_id);
}
