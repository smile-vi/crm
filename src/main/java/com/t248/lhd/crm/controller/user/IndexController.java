package com.t248.lhd.crm.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping(value = "/login")
    public String login(){
        System.out.println("进入login页面");
        return "login";
    }
    @RequestMapping(value = "/main")
    public String main(){
        System.out.println("进入main页面");
        return "main";
    }
    @RequestMapping(value = "/403")
    public String unauthorizedRole(){
        System.out.println("没有权限");
        return "403";
    }
}
