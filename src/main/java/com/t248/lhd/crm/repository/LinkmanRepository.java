package com.t248.lhd.crm.repository;

import com.t248.lhd.crm.entity.Chance;
import com.t248.lhd.crm.entity.Customer;
import com.t248.lhd.crm.entity.Linkman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface LinkmanRepository extends JpaRepository<Linkman,Long>{
    List<Linkman> findByLkmCustNo(String lkmCustNo);
    Linkman findLinkmanByLkmId(Long lkmId);
    @Transactional
    @Modifying
    @Query("update Linkman hy set " +
            "hy.lkmName = CASE WHEN :#{#linkman.lkmName} IS NULL THEN hy.lkmName ELSE :#{#linkman.lkmName} END ," +
            "hy.lkmSex = CASE WHEN :#{#linkman.lkmSex} IS NULL THEN hy.lkmSex ELSE :#{#linkman.lkmSex} END ," +
            "hy.lkmPostion = CASE WHEN :#{#linkman.lkmPostion} IS NULL THEN hy.lkmPostion ELSE :#{#linkman.lkmPostion} END ," +
            "hy.lkmMobile = CASE WHEN :#{#linkman.lkmMobile} IS NULL THEN hy.lkmMobile ELSE :#{#linkman.lkmMobile} END ," +
            "hy.lkmMemo = CASE WHEN :#{#linkman.lkmMemo} IS NULL THEN hy.lkmMemo ELSE :#{#linkman.lkmMemo} END ," +
            "hy.lkmCustName = CASE WHEN :#{#linkman.lkmCustName} IS NULL THEN hy.lkmCustName ELSE :#{#linkman.lkmCustName} END ," +
            "hy.lkmCustNo = CASE WHEN :#{#linkman.lkmCustNo} IS NULL THEN hy.lkmCustNo ELSE :#{#linkman.lkmCustNo} END ," +
            "hy.lkmTel =  CASE WHEN :#{#linkman.lkmTel} IS NULL THEN hy.lkmTel ELSE :#{#linkman.lkmTel} END " +
            "where hy.lkmId = :#{#linkman.lkmId}")
    void updateLinkman(@Param("linkman") Linkman linkman);
}
