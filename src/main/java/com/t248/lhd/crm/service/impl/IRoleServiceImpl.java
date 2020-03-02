package com.t248.lhd.crm.service.impl;

import com.t248.lhd.crm.entity.Role;
import com.t248.lhd.crm.entity.SysRoleRight;
import com.t248.lhd.crm.repository.RoleRepository;
import com.t248.lhd.crm.repository.SysRIghtRoleRepository;
import com.t248.lhd.crm.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IRoleServiceImpl implements IRoleService {
    @Resource
    private RoleRepository roleRepository;
    @Resource
    private SysRIghtRoleRepository sysRIghtRoleRepository;
    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getRole(Long roleId) {
        return roleRepository.getOne(roleId);
    }

    @Override
    public void deleteRole(Long roleId) {
        roleRepository.deleteById(roleId);
    }

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findRolesByRoleName(roleName);
    }

    @Override
    public void addSysRightRole(String rf_right_code ,Long rf_role_id) {
        sysRIghtRoleRepository.addSysRight( rf_right_code , rf_role_id);
    }
}
