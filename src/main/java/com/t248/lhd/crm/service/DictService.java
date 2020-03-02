package com.t248.lhd.crm.service;

import com.t248.lhd.crm.entity.Dict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DictService {
     List<Dict> findByDictType(String dictType);
    Page<Dict> findDict(Long dictId, String dictItem,String dictType, Pageable pageable);
}
