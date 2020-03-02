package com.t248.lhd.crm.repository;

import com.t248.lhd.crm.entity.Role;
import com.t248.lhd.crm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findRoleByUsers(User user);
    public List<Role> findRolesByRoleNameLike(String roleName);
    Role findRolesByRoleName(String roleName);
}
