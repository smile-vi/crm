package com.t248.lhd.crm.controller.dict;

import com.t248.lhd.crm.entity.Dict;
import com.t248.lhd.crm.service.DictService;
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
public class DictController {
    @Resource
    private DictService dictService;
    @RequestMapping(value = "/dict/leverlist")
    public String listLever(Long dictId,String dictItem,
                            @RequestParam(required = false,defaultValue = "1") int pageIndex, Model model){
        Sort sort=Sort.by(Sort.Direction.ASC,"dictId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        String dictType="客户等级";
        Page<Dict> dictPage=dictService.findDict(dictId,dictItem,dictType,pageable);
        model.addAttribute("dictPage",dictPage);
        model.addAttribute("dictItem",dictItem);
        model.addAttribute("dictId",dictId);
        return "dict/leverlist";
    }
    @RequestMapping(value = "/dict/servicelist")
    public String serviceList(Long dictId,String dictItem,
                            @RequestParam(required = false,defaultValue = "1") int pageIndex, Model model){
        Sort sort=Sort.by(Sort.Direction.ASC,"dictId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        String dictType="服务类型";
        Page<Dict> dictPage=dictService.findDict(dictId,dictItem,dictType,pageable);
        model.addAttribute("servicePage",dictPage);
        model.addAttribute("dictItem",dictItem);
        model.addAttribute("dictId",dictId);
        return "dict/servicelist";
    }
    @RequestMapping(value = "/dict/regionlist")
    public String regionList(Long dictId,String dictItem,
                              @RequestParam(required = false,defaultValue = "1") int pageIndex, Model model){
        Sort sort=Sort.by(Sort.Direction.ASC,"dictId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        String dictType="地区";
        Page<Dict> dictPage=dictService.findDict(dictId,dictItem,dictType,pageable);
        model.addAttribute("regionPage",dictPage);
        model.addAttribute("dictItem",dictItem);
        model.addAttribute("dictId",dictId);
        return "dict/regionlist";
    }


}
