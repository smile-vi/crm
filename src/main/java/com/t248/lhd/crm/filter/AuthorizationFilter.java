package com.t248.lhd.crm.filter;


import com.t248.lhd.crm.service.TokenService;
import com.t248.lhd.crm.tools.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
public class AuthorizationFilter implements HandlerInterceptor {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入拦截器!!!");
        Cookie[] cookies = request.getCookies();
        if (cookies.length != 0) {
            List<Cookie> collect = Arrays.stream(cookies)
                    .filter(c -> "token_name".equals(c.getName())).collect(Collectors.toList());
            if (collect != null && collect.size() != 0) {
                String token = collect.get(0).getValue();
                System.out.println("token:" + token);

                redisUtil.expire(token, 60*60*2);
                if(!redisUtil.hasKey(token)){
                    System.out.println("拦截器拦截请求");
                    throw new RuntimeException("未登录");
                }
                /*if(tokenService.hasKey(token)){

                }*/
            } else {
                System.out.println("拦截器拦截请求");

                throw new RuntimeException("未登录");
            }
        }
        System.out.println("拦截器放行");
        return true;
    }
}
