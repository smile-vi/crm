package com.t248.lhd.crm.repository;
import com.t248.lhd.crm.entity.Chance;
import com.t248.lhd.crm.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan,Long> {
    List<Plan> findByPlaChcId(Long plaChcId);
    @Transactional
    @Modifying
    @Query("update Chance hy set " +
            "hy.chcStatus =  CASE WHEN :#{#huaYangArea.chcStatus} IS NULL THEN hy.chcStatus ELSE :#{#huaYangArea.chcStatus} END " +
            "where hy.chcId = :#{#huaYangArea.chcId}")
    int updateSuccess(@Param("huaYangArea") Chance Chance);
}
