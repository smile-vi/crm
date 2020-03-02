package com.t248.lhd.crm.repository;

import com.t248.lhd.crm.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity,Long> {
    List<Activity> findByAtvCustNo(String atvCustNo);
}
