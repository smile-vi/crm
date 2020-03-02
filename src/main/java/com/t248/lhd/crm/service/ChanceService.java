package com.t248.lhd.crm.service;

import com.t248.lhd.crm.entity.Chance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChanceService {
    Page<Chance> findChance(String chcCustName, String chcTitle, Pageable pageable);

    /**
     * 查询客户开发计划
     * @param chcCustName
     * @param chcTitle
     * @param chcLinkman
     * @param pageable
     * @return
     */
    Page<Chance> findChance(String chcCustName, String chcTitle,String chcLinkman, Pageable pageable);
     void saveChance(Chance chance);
    Chance getChance(Long chcId);
    void deleteChance(Long chcId);
    void updateStatus(Chance chance);

}
