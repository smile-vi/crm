package com.t248.lhd.crm.service.impl;

import com.t248.lhd.crm.entity.*;
import com.t248.lhd.crm.repository.*;
import com.t248.lhd.crm.service.CustomerService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerRepository customerRepository;
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private LinkmanRepository linkmanRepository;
    @Resource
    private ActivityRepository activityRepository;
    @Resource
    private LostRepository lostRepository;

    @Override
    public Page<Customer> findCustomer(String custName, String custNo, String custRegion, String custManagerName,
                                       String custLevelLabel, Pageable pageable) {
        Specification specification=new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                if (custName!=null&&!custName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("custName"),"%"+custName+"%"));
                }
                if (custNo!=null&&!custNo.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("custNo"),"%"+custNo+"%"));
                }
                if (custRegion!=null&&!custRegion.equals("")){
                    predicates.add(criteriaBuilder.equal(root.get("custRegion"),custRegion));
                }
                if (custManagerName!=null&&!custManagerName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("custManagerName"),"%"+custManagerName+"%"));
                }
                if (custLevelLabel!=null&&!custLevelLabel.equals("")){
                    predicates.add(criteriaBuilder.equal(root.get("custLevelLabel"),custLevelLabel));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return customerRepository.findAll(specification,pageable);
    }

    @Override
    public List<Linkman> listMan(String lkmCustNo) {
        return linkmanRepository.findByLkmCustNo(lkmCustNo);
    }

    @Override
    public Linkman findBylkmId(Long lkmCustNo) {
        return linkmanRepository.findLinkmanByLkmId(lkmCustNo);
    }

    @Override
    public void updateLinkman(Linkman linkman) {
         linkmanRepository.updateLinkman(linkman);
    }

    @Override
    public void saveLinkman(Linkman linkman) {
        linkmanRepository.save(linkman);
    }

    @Override
    public void delLinkman(Long lkmId) {
        linkmanRepository.deleteById(lkmId);
    }

    @Override
    public List<Activity> findActivityByAtvCustNo(String atvCustNo) {
        return activityRepository.findByAtvCustNo(atvCustNo);
    }

    @Override
    public Page<Lost> findLost(String lstCustName, String lstCustManagerName, String lstStatus, Pageable pageable) {
        Specification specification=new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                if (lstCustName!=null&&!lstCustName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("lstCustName"),"%"+lstCustName+"%"));
                }
                if (lstCustManagerName!=null&&!lstCustManagerName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("lstCustManagerName"),"%"+lstCustManagerName+"%"));
                }
                if (lstStatus!=null&&!lstStatus.equals("")){
                    predicates.add(criteriaBuilder.equal(root.get("lstStatus"),lstStatus));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return lostRepository.findAll(specification,pageable);
    }

    @Override
    public List<Lost> findLstStatus() {
        return lostRepository.findAll();
    }

    @Override
    public Lost findByLstId(Long lstId) {
        return lostRepository.findByLstId(lstId);
    }

    @Override
    public void updatePoneLost(Lost lost) {
        lostRepository.save(lost);
    }

    @Override
    public List<Customer> findByCustomerName() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findByCustName(String custName) {
        return customerRepository.findByCustName(custName);
    }@Override
    public Customer findByCustNo(String custNo) {
        return customerRepository.findByCustNo(custNo);
    }

    @Override
    public List<Orders> findByOrders(String custNo) {
        return orderRepository.findByOdrCustomerNo(custNo);
    }

    @Override
    public Lost findLost(String custNo) {
        return lostRepository.findByLstCustNo(custNo);
    }

    @Override
    public Page<Customer> findDo(String custName, Pageable pageable) {
        Specification<Customer> specification= new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(custName!=null && !custName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("custName"),"%"+custName+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return customerRepository.findAll(specification,pageable);
    }
    @Override
    public XSSFWorkbook show() {
        //查询数据的方法调用   重点
        List<Customer> list = customerRepository.findAll();//查出数据库数据
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Goods");//创建一张表
        Row titleRow = sheet.createRow(0);//创建第一行，起始为0
        titleRow.createCell(0).setCellValue("客户编号");//第一列
        titleRow.createCell(1).setCellValue("客户名称"); //title标题
        titleRow.createCell(2).setCellValue("客户等级");
        titleRow.createCell(3).setCellValue("客户地址");
        int cell = 1;
        for (Customer cst : list) {
            //注意控制行
            Row row = sheet.createRow(cell);//从第二行开始保存数据
            row.createCell(0).setCellValue(cst.getCustNo());
            row.createCell(1).setCellValue(cst.getCustName());//将数据库的数据遍历出来
            row.createCell(2).setCellValue(cst.getCustLevelLabel());
            row.createCell(3).setCellValue(cst.getCustAddr());
            cell++;
        }
        return wb;
    }

}
