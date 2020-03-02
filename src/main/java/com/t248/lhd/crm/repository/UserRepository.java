package com.t248.lhd.crm.repository;

import com.t248.lhd.crm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.beans.Transient;

public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
    public User findByUsrNameAndUsrPassword(String usrName,String usrPassword);
    @Transactional
    @Query(value = "delete from sys_user where usr_id=?1",nativeQuery = true)
    @Modifying
    public void deleteUser(Long usrId);
    User findUserByUsrName(String usrName);
}
