package com.t248.lhd.crm.repository;

import com.t248.lhd.crm.entity.Lost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LostRepository extends JpaRepository<Lost,Long>, JpaSpecificationExecutor<Lost> {
    List<Lost> findAll();
    Lost findByLstId(Long lstId);
    Lost findByLstCustNo(String lstCustNo);
}
