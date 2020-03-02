package com.t248.lhd.crm.repository;

import com.t248.lhd.crm.entity.CusService;
import com.t248.lhd.crm.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StorageRepository extends JpaRepository<Storage,Long>, JpaSpecificationExecutor<Storage> {
}
