package com.t248.lhd.crm.controller.customer;

import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.Title;
import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.series.Pie;
import com.t248.lhd.crm.entity.*;
import com.t248.lhd.crm.service.CustomerService;
import com.t248.lhd.crm.service.DictService;
import com.t248.lhd.crm.service.OrderService;
import org.aspectj.weaver.ast.Or;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {
    @Resource
    private CustomerService customerService;
    @Resource
    private DictService dictService;
    @Resource
    private OrderService orderService;
    @RequestMapping(value = "customer/customerlist")
    public String customerList(String custName, String custNo, String custRegion, String custManagerName, String custLevelLabel,Model model,@RequestParam(required = false,defaultValue = "1") int pageIndex){
        Sort sort=Sort.by(Sort.Direction.ASC,"custId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        Page<Customer> customerPage=customerService.findCustomer(custName,custNo,custRegion,custManagerName,custLevelLabel,pageable);
        List<Dict> dictList=dictService.findByDictType("地区");
        List<Dict> leverList=dictService.findByDictType("客户等级");
        model.addAttribute("customerPage",customerPage);
        model.addAttribute("custName",custName);
        model.addAttribute("custNo",custNo);
        model.addAttribute("custRegion",custRegion);
        model.addAttribute("custManagerName",custManagerName);
        model.addAttribute("custLevelLabel",custLevelLabel);
        model.addAttribute("dictList",dictList);
        model.addAttribute("leverList",leverList);
        return "customer/list";
    }
    @RequestMapping(value = "customer/linkman")
    public String linkmanList(String custName, String custNo,Model model,@RequestParam(required = false,defaultValue = "1") int pageIndex){


        List<Linkman> linkmanList=customerService.listMan(custNo);
        model.addAttribute("custName",custName);
        model.addAttribute("custNo",custNo);
        model.addAttribute("linkmanList",linkmanList);
        return "customer/linkman";
    }
    @RequestMapping(value = "customer/editlinkman")
    public String editLinkman(Model model,Long lkmId){
        Linkman linkman=customerService.findBylkmId(lkmId);
        model.addAttribute("linkman",linkman);
        return "customer/editlinkman";
    }
    @RequestMapping(value = "customer/editlinkmansave")
    public String editLinkmanSave(Linkman linkman){
        System.out.println(linkman.getLkmSex());
        customerService.updateLinkman(linkman);
        return "redirect:customerlist";
    }
    @RequestMapping(value = "customer/addlinkman")
    public String addLinkman(String custName,String custNo,Model model){
        model.addAttribute("custName",custName);
        model.addAttribute("lkmCustNo",custNo);
        return "customer/addlinkman";
    }

    /**
     * 新增联系人
     * @param linkman
     * @return
     */
    @RequestMapping(value = "customer/addlinkmansave")
    public String addLinkmanSave(Linkman linkman){
        customerService.saveLinkman(linkman);
        return "redirect:customerlist";
    }

    /**
     * 删除联系人
     * @param lkmId
     * @return
     */
    @RequestMapping(value = "customer/dellinkman")
    @ResponseBody
    public Map delLinkman(Long lkmId){
        customerService.delLinkman(lkmId);
        Map map=new HashMap();
        map.put("delResult","true");
        return map;
    }

    /**
     * 交往记录
     * @param custNo
     * @param custName
     * @param model
     * @return
     */
    @RequestMapping(value = "customer/activity")
    public String activityList(String custNo,String custName,Model model){
        List<Activity> activityList=customerService.findActivityByAtvCustNo(custNo);
        model.addAttribute("custNo",custNo);
        model.addAttribute("custName",custName);
        model.addAttribute("activityList",activityList);
        return "activity/list";
    }

    /**
     * 客户流失列表
     * @param model
     * @return
     */
    @RequestMapping(value = "customer/lostlist")
    public String lostList(Model model,String lstCustName, String lstCustManagerName, String lstStatus,@RequestParam(required = false,defaultValue = "1") int pageIndex){
        Sort sort=Sort.by(Sort.Direction.ASC,"lstId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        Page<Lost> lostList=customerService.findLost(lstCustName,lstCustManagerName,lstStatus,pageable);
        List<Lost> losts=customerService.findLstStatus();
        model.addAttribute("lostPage",lostList);
        model.addAttribute("losts",losts);
        model.addAttribute("lstCustName",lstCustName);
        model.addAttribute("lstCustManagerName",lstCustManagerName);
        model.addAttribute("lstStatus",lstStatus);
        return "customer/lostlist";
    }

    /**
     * 暂缓流失
     * @param model
     * @param lstId
     * @return
     */
    @RequestMapping(value = "customer/postponelost")
    public String postponeLost(Model model,Long lstId){
        Lost lost=customerService.findByLstId(lstId);
        model.addAttribute("lost",lost);
        return "customer/postponelost";
    }

    /**
     * 确认流失
     * @return
     */
    @RequestMapping(value = "customer/confirmlost")
    public String confirmLost(Model model,Long lstId){
        Lost lost=customerService.findByLstId(lstId);
        model.addAttribute("lost",lost);
        return "customer/confirmlost";
    }
    @RequestMapping(value = "customer/save")
    public String saveLostPone(Lost lost){
        customerService.updatePoneLost(lost);
        return "redirect:lostlist";
    }
    @RequestMapping(value = "customer/orderslist")
    public String ordersList(Model model,String custNo,String custName){
        List<Orders> ordersList= customerService.findByOrders(custNo);
        model.addAttribute("custName",custName);
        model.addAttribute("custNo",custNo);
        model.addAttribute("orderList",ordersList);
        return "customer/orders/list";
    }
    @RequestMapping(value = "statement/lostlist")
    public String lostListTongji(Model model,String lstCustName, String lstCustManagerName, String lstStatus,@RequestParam(required = false,defaultValue = "1") int pageIndex){
        Sort sort=Sort.by(Sort.Direction.ASC,"lstId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        Page<Lost> lostList=customerService.findLost(lstCustName,lstCustManagerName,"",pageable);
        List<Lost> losts=customerService.findLstStatus();
        model.addAttribute("lostPage",lostList);
        model.addAttribute("losts",losts);
        model.addAttribute("lstCustName",lstCustName);
        model.addAttribute("lstCustManagerName",lstCustManagerName);
        return "statement/lostlist";
    }

    @RequestMapping(value = "statement/contrRpList")
    public String customerGX(String svrCustName,Model model,@RequestParam(required = false,defaultValue = "1") int pageIndex){
        Map<String,Double> map = new HashMap<String,Double>();
        Pageable pageable = PageRequest.of(pageIndex-1,3);
        Page<Customer> pager = customerService.findDo(svrCustName,pageable);
        for (Customer customer: pager.getContent()) {
            Double sum = 0.0;
            for (Orders o: orderService.findByOdrCustomerNo(customer.getCustNo())) {
                for (OrdersLine ordersLine:o.getOrdersLines()){
                    sum+=ordersLine.getOddPrice();
                }
            }

            map.put(customer.getCustId()+"",sum);
        }
        model.addAttribute("pager",pager);
        model.addAttribute("map",map);
        model.addAttribute("svrCustName",svrCustName);
        return "statement/contrRptList";
    }
    @RequestMapping("statement/tub")
    @ResponseBody
    public Option getEchartsPieOption(){

        List<Customer> list = customerService.findByCustomerName();
        Option option = new Option();
        //标题
        Title title = new Title();
        title.setText("客户贡献图");
        title.setX("center");
        option.setTitle(title);

        //提示框
        Tooltip tooltip = new Tooltip();
        tooltip.setTrigger(Trigger.item);
        tooltip.formatter("{a} <br/>{b} : {c} ({d}%)");
        option.setTooltip(tooltip);

        //Legend
        Legend legend = new Legend();
        List<String> lenthData=new ArrayList<>();
        legend.setOrient(Orient.vertical);
        legend.show(false);
        legend.setLeft("right");
        option.setLegend(legend);
        //饼状图
        Pie pie = new Pie();
        //对数据进行简单处理
        List<Map> mapList = new ArrayList<>();
        for (Customer customer: list) {
            Double ds = new Double(0);
            Map<String,String> addMap = new HashMap<>();
            addMap.put("name",customer.getCustName());
            for (Orders orders:orderService.findByOdrCustomerNo(customer.getCustNo())){
                for (OrdersLine line:
                        orders.getOrdersLines()) {
                    ds+=line.getOddPrice();
                }
            }

            addMap.put("value",String.valueOf(ds));
            mapList.add(addMap);
        }


        //封装pie
        pie.setData(mapList);
        pie.setName("上映年代");
        pie.setRadius("55%");
        String [] centerArray = {"50%","60%"};
        pie.setCenter(centerArray);

        option.series(pie);

        return option;
    }


}
