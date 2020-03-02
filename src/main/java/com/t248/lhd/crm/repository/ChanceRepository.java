package com.t248.lhd.crm.repository;

import com.t248.lhd.crm.entity.Chance;
import com.t248.lhd.crm.entity.CusService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ChanceRepository extends JpaRepository<Chance,Long>, JpaSpecificationExecutor<CusService> {
    Chance getByChcId(Long chcId);
    @Transactional
    @Modifying
   /* @Query("update Chance set chcDueId=#{chance.chcDueId},chcDueTo=#{chance.chcDueTo},chcDueDate=#{chance.chcDueDate},chcStatus=#{chance.chcStatus}  where chcId=#{chance.chcId}")
    int updateStatus(@Param("chance") Chance chance);*/
    @Query("update Chance hy set " +
            "hy.chcDueId = CASE WHEN :#{#huaYangArea.chcDueId} IS NULL THEN hy.chcDueId ELSE :#{#huaYangArea.chcDueId} END ," +
            "hy.chcDueTo = CASE WHEN :#{#huaYangArea.chcDueTo} IS NULL THEN hy.chcDueTo ELSE :#{#huaYangArea.chcDueTo} END ," +
            "hy.chcDueDate = CASE WHEN :#{#huaYangArea.chcDueDate} IS NULL THEN hy.chcDueDate ELSE :#{#huaYangArea.chcDueDate} END ," +
            "hy.chcStatus =  CASE WHEN :#{#huaYangArea.chcStatus} IS NULL THEN hy.chcStatus ELSE :#{#huaYangArea.chcStatus} END " +
            "where hy.chcId = :#{#huaYangArea.chcId}")
    int updateStatus(@Param("huaYangArea") Chance Chance);


}
