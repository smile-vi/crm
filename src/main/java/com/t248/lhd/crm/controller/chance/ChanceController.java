package com.t248.lhd.crm.controller.chance;

import com.t248.lhd.crm.entity.Chance;
import com.t248.lhd.crm.entity.CusService;
import com.t248.lhd.crm.entity.Dict;
import com.t248.lhd.crm.entity.User;
import com.t248.lhd.crm.service.ChanceService;
import com.t248.lhd.crm.service.CusServiceService;
import com.t248.lhd.crm.service.DictService;
import com.t248.lhd.crm.service.IUserService;
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
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ChanceController {
    @Resource
    private ChanceService chanceService;
    @Resource
    private DictService dictService;
    @Resource
    private IUserService userService;

    /**
     * 新增
     * @param chcCustName
     * @param chcTitle
     * @param pageIndex
     * @param model
     * @return
     */
    @RequestMapping(value = "/chance/list")
    public String serviceList(String chcCustName, String chcTitle,
                              @RequestParam(required = false,defaultValue = "1") int pageIndex, Model model){
        System.out.println("进入销售管理页面");

        Sort sort=Sort.by(Sort.Direction.ASC,"chcId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        Page<Chance> userPage=chanceService.findChance(chcCustName,chcTitle,pageable);
        model.addAttribute("chancePage",userPage);
        model.addAttribute("svrTitle",chcCustName);
        model.addAttribute("svrCustName",chcTitle);
        return "chance/list";
    }


    @RequestMapping(value = "/chance/add")
    public String chanceAdd(Model model){
        List<Dict> levelList=dictService.findByDictType("客户等级");
        List<Dict> fromList=dictService.findByDictType("地区");
        List<User> userList=userService.findUsers();
        model.addAttribute("levelList",levelList);
        model.addAttribute("fromList",fromList);
        model.addAttribute("userList",userList);
        return "chance/add";
    }

    /**
     * 新增
     * @param chance
     * @param session
     * @return
     */
    @RequestMapping(value = "/chance/save")
    public String chanceSave(Chance chance, HttpSession session){
        User user=(User)session.getAttribute("user");
        chance.setChcCreateBy(user.getUsrName());
        chance.setChcCreateId(user.getUsrId());
        Long a=chance.getChcDueId().longValue();
        User dueTo=userService.getUser(a);
        chance.setChcDueTo(dueTo.getUsrName());
        chance.setChcCreateDate(new Date());
        chanceService.saveChance(chance);
        return "redirect:/chance/list";
    }
    @RequestMapping(value = "/assignChance/save")
    public String chanceAssignSave(Chance chance, HttpSession session){
        Long a=chance.getChcDueId().longValue();
        User dueTo=userService.getUser(a);
        chance.setChcDueTo(dueTo.getUsrName());
        chance.setChcDueId(dueTo.getUsrId().intValue());
        chanceService.updateStatus(chance);
        return "redirect:/chance/list";
    }
    /**
     * 更新
     * @param chcId
     * @param model
     * @return
     */
    @RequestMapping(value = "/chance/edit")
    public String chanceEdit(Long chcId,Model model){
        List<Dict> levelList=dictService.findByDictType("客户等级");
        List<Dict> fromList=dictService.findByDictType("地区");
        List<User> userList=userService.findUsers();
        Chance chance=chanceService.getChance(chcId);
        model.addAttribute("chance",chance);
        model.addAttribute("levelList",levelList);
        model.addAttribute("fromList",fromList);
        model.addAttribute("userList",userList);
        return "chance/edit";
    }

    /**
     * 删除
     * @param chcId
     * @return
     */
    @RequestMapping(value = "/chance/del")
    @ResponseBody
    public Map del(Long chcId){
        chanceService.deleteChance(chcId);
        Map map=new HashMap();
        map.put("delResult","true");
        return map;
    }

    /**
     * 指派销售机会
     * @param chcId
     * @return
     */
    @RequestMapping(value = "/chance/assign")
    public String assignPlan(Long chcId,Model model){
        Chance chance=chanceService.getChance(chcId);
        List<User> userList=userService.findUsers();
        model.addAttribute("chance",chance);
        model.addAttribute("userList",userList);
        return "chance/assign";
    }

}
