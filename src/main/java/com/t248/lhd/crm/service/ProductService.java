package com.t248.lhd.crm.service;

import com.t248.lhd.crm.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public interface ProductService {
    Page<Product> findProduct(String prodName, String prodType, String prodBatch, Pageable pageable);
}
