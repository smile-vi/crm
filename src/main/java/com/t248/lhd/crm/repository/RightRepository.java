package com.t248.lhd.crm.repository;

import com.t248.lhd.crm.entity.Right;
import com.t248.lhd.crm.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RightRepository extends JpaRepository<Right,Long> {
    List<Right> findRightsByRoles(Role role);
    List<Right> findByRightType(String rightType);


}
