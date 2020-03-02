package com.t248.lhd.crm.controller.chance;

import com.t248.lhd.crm.entity.Chance;
import com.t248.lhd.crm.entity.Plan;
import com.t248.lhd.crm.service.ChanceService;
import com.t248.lhd.crm.service.DictService;
import com.t248.lhd.crm.service.IUserService;
import com.t248.lhd.crm.service.PlanService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PlanController {
    @Resource
    private ChanceService chanceService;
    @Resource
    private PlanService planService;
    @RequestMapping(value = "/chance/plan")
    public String planList(String chcCustName, String chcTitle,String chcLinkman,@RequestParam(required = false,defaultValue = "1") int pageIndex, Model model){
        System.out.println("进入客户开发计划页面");

        Sort sort=Sort.by(Sort.Direction.ASC,"chcId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        Page<Chance> userPage=chanceService.findChance(chcCustName,chcTitle,pageable);
        model.addAttribute("chancePage",userPage);
        model.addAttribute("chcCustName",chcCustName);
        model.addAttribute("chcTitle",chcTitle);
        model.addAttribute("chcLinkman",chcLinkman);
        return "plan/list";
    }
    @RequestMapping(value = "/plan/enact")
    public String enactPlan(Model model,Long chcId){
        Chance chance=chanceService.getChance(chcId);
        List<Plan> planList=planService.findPaln(chcId);
        model.addAttribute("chance",chance);
        model.addAttribute("planList",planList);
        return "plan/enact";
    }
    @RequestMapping(value = "/plan/execute")
    public String executePlan(Model model,Long chcId){
        Chance chance=chanceService.getChance(chcId);
        List<Plan> planList=planService.findPaln(chcId);
        model.addAttribute("chance",chance);
        model.addAttribute("planList",planList);
        return "plan/execute";
    }
    /**
     * 删除
     * @param plaId
     * @return
     */
    @RequestMapping(value = "/plan/del")
    @ResponseBody
    public Map del(Long plaId){
        planService.delPlan(plaId);
        Map map=new HashMap();
        map.put("delResult","true");
        return map;
    }

    /**
     * 保存计划项
     * @return
     */
    @RequestMapping(value = "/plan/enactsave")
    public String saveEnact(Plan plan){
        planService.savePlan(plan);
        return "redirect:/chance/plan";
    }
    @RequestMapping(value = "/plan/success")
    public String successPlan(Model model,Long chcId){
        Chance chance=chanceService.getChance(chcId);
        List<Plan> planList=planService.findPaln(chcId);
        model.addAttribute("chance",chance);
        model.addAttribute("planList",planList);
        return "plan/success";
    }
    @RequestMapping(value = "/plan/successsave")
    @ResponseBody
    public Map successSave(Long chcId){
        Chance chance=new Chance();
        chance.setChcId(chcId);
        chance.setChcStatus("已归档");
        planService.updateSuccess(chance);
        Map map=new HashMap();
        map.put("successResult","true");
        return map;
    }
    @RequestMapping(value = "/plan/terminationsave")
    @ResponseBody
    public Map terminationsaveSave(Long chcId){
        Chance chance=new Chance();
        chance.setChcId(chcId);
        chance.setChcStatus("已归档");
        planService.updateSuccess(chance);
        Map map=new HashMap();
        map.put("successResult","true");
        return map;
    }
}
