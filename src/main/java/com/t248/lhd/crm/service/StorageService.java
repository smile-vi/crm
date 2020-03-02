package com.t248.lhd.crm.service;

import com.t248.lhd.crm.entity.Storage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public interface StorageService {
    Page<Storage> findStorage(String prodName, String stkWarehouse, Pageable pageable);


}
