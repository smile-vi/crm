package com.t248.lhd.crm.controller.product;

import com.t248.lhd.crm.entity.Dict;
import com.t248.lhd.crm.entity.Product;
import com.t248.lhd.crm.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class ProductController {
    @Resource
    private ProductService productService;
    @RequestMapping(value = "dict/productlist")
    public String productList(String prodName, String prodType, String prodBatch,
                              @RequestParam(required = false,defaultValue = "1") int pageIndex, Model model){
        Sort sort=Sort.by(Sort.Direction.ASC,"prodId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        Page<Product> productPage=productService.findProduct(prodName,prodType,prodBatch,pageable);
        model.addAttribute("productPage",productPage);
        model.addAttribute("prodName",prodName);
        model.addAttribute("prodType",prodType);
        model.addAttribute("prodBatch",prodBatch);
        return "dict/productlist";
    }
}
