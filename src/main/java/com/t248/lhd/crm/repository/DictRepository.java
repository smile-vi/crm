package com.t248.lhd.crm.repository;

import com.t248.lhd.crm.entity.Dict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DictRepository extends JpaRepository<Dict,Long>, JpaSpecificationExecutor<Dict> {
    /**
     * 根据服务类型查找
     * @param dictType
     * @return
     */
    public List<Dict> findByDictType(String dictType);

}
