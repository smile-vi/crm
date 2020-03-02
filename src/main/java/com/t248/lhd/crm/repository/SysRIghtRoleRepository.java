package com.t248.lhd.crm.repository;

import com.t248.lhd.crm.entity.Storage;
import com.t248.lhd.crm.entity.SysRoleRight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface SysRIghtRoleRepository extends JpaRepository<SysRoleRight,Long> {
    @Transactional
    @Modifying
    @Query(value = "insert into sys_role_right (rf_right_code, rf_role_id) values (?1,?2) ",nativeQuery = true)
    void addSysRight(String rf_right_code ,Long rf_role_id);

}
