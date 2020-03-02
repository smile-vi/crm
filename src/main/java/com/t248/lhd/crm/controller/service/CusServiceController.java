package com.t248.lhd.crm.controller.service;

import com.alibaba.fastjson.JSON;
import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.Title;
import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.series.Pie;
import com.t248.lhd.crm.entity.*;
import com.t248.lhd.crm.service.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

@Controller
public class CusServiceController {
    @Resource
    private CusServiceService cusServiceService;
    @Resource
    private CustomerService customerService;
    @Resource
    private DictService dictService;
    @Resource
    private IUserService userService;
    @RequestMapping(value = "/service/management")
    public String serviceList(String svrTitle, String svrCustName,
                              @RequestParam(required = false,defaultValue = "1") int pageIndex, Model model){
        System.out.println("进入销售管理页面");

        Sort sort=Sort.by(Sort.Direction.ASC,"svrId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        Page<CusService> userPage=cusServiceService.findCusService(svrTitle,svrCustName,pageable);
        model.addAttribute("userPage",userPage);
        model.addAttribute("svrTitle",svrTitle);
        model.addAttribute("svrCustName",svrCustName);
        return "service/list";
    }

    /**
     * 服务创建
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "servicemanagement/addservice")
    public String addService(Model model, HttpServletRequest request){
        List<Customer> customerList=customerService.findByCustomerName();
        List<Dict> dictList=dictService.findByDictType("服务类型");
        User user=(User) request.getSession().getAttribute("user");
        model.addAttribute("customeList",customerList);
        model.addAttribute("dictList",dictList);
        model.addAttribute("svrCreateBy",user.getUsrName());
        return "servicemanagement/addservice";
    }
    @RequestMapping(value = "servicemanagement/saveservice")
    public String saveService(CusService cusService,HttpServletRequest request){
        User user=(User) request.getSession().getAttribute("user");
        Customer customer=customerService.findByCustName(cusService.getSvrCustName());
        cusService.setCustomer(customer);
        cusService.setSvrCreateId(user.getUsrId().intValue());
        cusService.setSvrCreateDate(new Date());
        cusServiceService.saveService(cusService);
        //新增成功后跳转到服务分配
        return "redirect:allocation_service";
    }

    /**
     * 服务分配
     * @param model
     * @return
     */
    @RequestMapping(value = "servicemanagement/allocation_service")
    public String allocationServiceList(String svrTitle, String svrCustName,String svrType,
                                        @RequestParam(required = false,defaultValue = "1") int pageIndex, Model model){
        Sort sort=Sort.by(Sort.Direction.ASC,"svrId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        String svrStatus="新创建";
        Page<CusService> servicePage=cusServiceService.findCusService(svrTitle,svrCustName,svrType,svrStatus,pageable);
        List<Dict> dictList=dictService.findByDictType("服务类型");
        List<Customer> customerList=customerService.findByCustomerName();
        model.addAttribute("svrTitle",svrTitle);
        model.addAttribute("svrCustName",svrCustName);
        model.addAttribute("svrType",svrType);
        model.addAttribute("dictList",dictList);
        model.addAttribute("customerList",customerList);
        model.addAttribute("servicePage",servicePage);
        return "servicemanagement/allocation_service";
    }

    @RequestMapping(value = "servicemanagement/allocation_servicesave")
    public String allocationServiceSave(CusService cusService){
        System.out.println("客户编号:"+cusService.getCustomer().getCustNo());
        Customer customer=customerService.findByCustNo(cusService.getCustomer().getCustNo());
        cusService.setSvrDueId(customer.getCustManagerId());
        cusService.setSvrDueTo(customer.getCustManagerName());
        cusService.setSvrDueDate(new Date());
        cusServiceService.saveService(cusService);
        return "redirect:allocation_service";
    }
    /**
     * 服务处理
     * @param model
     * @return
     */
    @RequestMapping(value = "servicemanagement/dispose_service")
    public String disposeServiceList(String svrTitle, String svrCustName,String svrType,
                                        @RequestParam(required = false,defaultValue = "1") int pageIndex, Model model){
        Sort sort=Sort.by(Sort.Direction.ASC,"svrId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        String svrStatus="已分配";
        Page<CusService> servicePage=cusServiceService.findCusService(svrTitle,svrCustName,svrType,svrStatus,pageable);
        List<Dict> dictList=dictService.findByDictType("服务类型");
        List<Customer> customerList=customerService.findByCustomerName();
        model.addAttribute("svrTitle",svrTitle);
        model.addAttribute("svrCustName",svrCustName);
        model.addAttribute("svrType",svrType);
        model.addAttribute("dictList",dictList);
        model.addAttribute("customerList",customerList);
        model.addAttribute("servicePage",servicePage);
        return "servicemanagement/dispose_service";
    }
    @RequestMapping(value = "servicemanagement/save_dispose_service")
    public String saveDisposeService(long svrId,Model model,HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        CusService cusService= cusServiceService.findBySvrId(svrId);
        model.addAttribute("service",cusService);
        return "servicemanagement/save_dispose_service";
    }

    /**
     * 服务处理保存
     * @param cusService
     * @return
     */
    @RequestMapping(value = "servicemanagement/dispose_servicesave")
    public String saveDisposeService(CusService cusService){
        cusService.setSvrDealDate(new Date());
        cusServiceService.saveService(cusService);
        return "redirect:dispose_service";
    }
    /**
     * 服务反馈
     * @param model
     * @return
     */
    @RequestMapping(value = "servicemanagement/feedbacklist_service")
    public String feedbackServiceList(String svrTitle, String svrCustName,String svrType,
                                     @RequestParam(required = false,defaultValue = "1") int pageIndex, Model model){
        Sort sort=Sort.by(Sort.Direction.ASC,"svrId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        String svrStatus="已处理";
        Page<CusService> servicePage=cusServiceService.findCusService(svrTitle,svrCustName,svrType,svrStatus,pageable);
        List<Dict> dictList=dictService.findByDictType("服务类型");
        List<Customer> customerList=customerService.findByCustomerName();
        model.addAttribute("svrTitle",svrTitle);
        model.addAttribute("svrCustName",svrCustName);
        model.addAttribute("svrType",svrType);
        model.addAttribute("dictList",dictList);
        model.addAttribute("customerList",customerList);
        model.addAttribute("servicePage",servicePage);
        return "servicemanagement/feedbacklist_service";
    }
    @RequestMapping(value = "servicemanagement/save_feedback_service")
    public String saveFeedBackService(long svrId,HttpServletRequest request,Model model){

        User user=(User)request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        CusService cusService= cusServiceService.findBySvrId(svrId);
        model.addAttribute("service",cusService);
        return "servicemanagement/save_feedback_service";

    }
    @RequestMapping(value = "servicemanagement/savefeedback_servicesave")
    public String saveFeedService(CusService cusService){
        Integer result=cusService.getSvrSatisfy();
            if (result>3){
                cusService.setSvrStatus("已归档");
            }else{
                cusService.setSvrStatus("已分配");
            }

        cusServiceService.saveService(cusService);
        return "redirect:feedbacklist_service";
    }
    /**
     * 服务反馈
     * @param model
     * @return
     */
    @RequestMapping(value = "servicemanagement/pigeonholelist_service")
        public String pigeonholeServiceList(String svrTitle, String svrCustName,String svrType,
        @RequestParam(required = false,defaultValue = "1") int pageIndex, Model model){
            Sort sort=Sort.by(Sort.Direction.ASC,"svrId");
            Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        String svrStatus="已归档";
        Page<CusService> servicePage=cusServiceService.findCusService(svrTitle,svrCustName,svrType,svrStatus,pageable);
        List<Dict> dictList=dictService.findByDictType("服务类型");
        List<Customer> customerList=customerService.findByCustomerName();
        model.addAttribute("svrTitle",svrTitle);
        model.addAttribute("svrCustName",svrCustName);
        model.addAttribute("svrType",svrType);
        model.addAttribute("dictList",dictList);
        model.addAttribute("customerList",customerList);
        model.addAttribute("servicePage",servicePage);
        return "servicemanagement/pigeonholelist_service";
    }
    @RequestMapping(value = "servicemanagement/check_pigeonhole_service")
    public String checkPigeonHoleService(long svrId,Model model){
        CusService cusService=cusServiceService.findBySvrId(svrId);
        model.addAttribute("service",cusService);
        return "servicemanagement/check_pigeonhole_service";
    }

    @RequestMapping(value = "statement/analyze_service")
    public String getService(Model model,@RequestParam(required = false,defaultValue = "1") int pageIndex){
        Pageable pageable= PageRequest.of(pageIndex-1,5);
        Page<Object[]> cusServiceList=cusServiceService.getHistoryInfo(pageable);
        model.addAttribute("servicePage",cusServiceList);
        return "statement/analyze_service";
    }
    @RequestMapping(value = "statement/echarsShow")
    @ResponseBody
    public List<DomainVo> echartsShow(){


        List<DomainVo> list=cusServiceService.getService();


        return list;
    }
    @Resource
    private OrderService orderService;
    @RequestMapping(value = "statement/excel")
    @ResponseBody
    public Map excel(){
        Map<String, Double> accts = new HashMap<String, Double>();
        List<Customer> list = customerService.findByCustomerName();
        List<Map> mapList = new ArrayList<>();
        for (Customer customer: list) {
            Double ds = new Double(0);
            for (Orders orders:orderService.findByOdrCustomerNo(customer.getCustNo())){
                for (OrdersLine line:
                        orders.getOrdersLines()) {
                    ds+=line.getOddPrice();
                }
            }

           accts.put(customer.getCustName(),ds);
        }





        // 创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        // 建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet("FXT");
        // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        // 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cellOne = row1.createCell(0);
        // 设置单元格内容
        cellOne.setCellValue("账号");
        HSSFCell cellTwo = row1.createCell(1);
        // 设置单元格内容
        cellTwo.setCellValue("金额");

        //行数
        int rowNum = 1;
        //遍历hashmap
        Iterator iterator = accts.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            //创建一行行记录
            rowNum++;
            // 在sheet里创建下一行
            HSSFRow newRow = sheet.createRow(rowNum);
            // 创建单元格并设置单元格内容
            newRow.createCell(0).setCellValue((String) key);
            newRow.createCell(1).setCellValue((Double) val);

        }
        Map<String,String> map=new HashMap();
        // 第六步，将文件存到指定位置
        try {
            String path = "D:/桌面/客户贡献列表.xlsx";
            File file = new File(path);
            //如果已经存在则删除
            if (file.exists()) {
                file.delete();
            }
            //检查父包是否存在
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            //创建文件
            file.createNewFile();
            FileOutputStream fout = new FileOutputStream(path);
            wb.write(fout);
            String str = "导出成功！";
            map.put("Result","true");
            System.out.println(str);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            String str1 = "导出失败！";
            map.put("Result","false");
            System.out.println(str1);
        }
        // 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        //sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));



        return map;
    }

    /**
     * 下载excel表格
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("statement/downExcel")
    public String downExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = ResourceUtils.getURL("classpath:").getPath();
        System.out.println(path);
        // 创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        // 建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet("成绩表");
        // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        // 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell = row1.createCell(0);
        // 设置单元格内容
        cell.setCellValue("学员考试成绩一览表");
        // 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
        // 在sheet里创建第二行
        HSSFRow row2 = sheet.createRow(1);
        // 创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("姓名");
        row2.createCell(1).setCellValue("班级");
        row2.createCell(2).setCellValue("笔试成绩");
        row2.createCell(3).setCellValue("机试成绩");
        // 在sheet里创建第三行
        HSSFRow row3 = sheet.createRow(2);
        row3.createCell(0).setCellValue("李明");
        row3.createCell(1).setCellValue("As178");
        row3.createCell(2).setCellValue(87);
        row3.createCell(3).setCellValue(78);
        // .....省略部分代码
        // 输出Excel文件
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=details.xls");
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
        return null;
    }




}
