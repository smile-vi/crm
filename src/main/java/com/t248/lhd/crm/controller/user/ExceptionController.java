package com.t248.lhd.crm.controller.user;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(RuntimeException.class)
    public String exception(RuntimeException e, Model model){
        if (e.getMessage().equals("未登录")){
            return "login";
        }
        System.out.println("异常捕获 "+e.getMessage());
        model.addAttribute("message",e.getMessage());
        return "/error";
    }
}
