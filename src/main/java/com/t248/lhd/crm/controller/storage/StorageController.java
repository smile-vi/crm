package com.t248.lhd.crm.controller.storage;

import com.t248.lhd.crm.entity.Product;
import com.t248.lhd.crm.entity.Storage;
import com.t248.lhd.crm.service.StorageService;
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
public class StorageController {
    @Resource
    private StorageService storageService;
    @RequestMapping(value = "/dict/storagelist")
    public String storageList(String prodName, String stkWarehouse,
                              @RequestParam(required = false,defaultValue = "1") int pageIndex, Model model){
        Sort sort=Sort.by(Sort.Direction.ASC,"stkId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        Page<Storage> productPage=storageService.findStorage(prodName,stkWarehouse,pageable);
        model.addAttribute("storagePage",productPage);
        model.addAttribute("prodName",prodName);
        model.addAttribute("stkWarehouse",stkWarehouse);
        return "dict/storagelist";
    }
}
