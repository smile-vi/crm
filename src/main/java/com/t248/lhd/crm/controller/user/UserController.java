package com.t248.lhd.crm.controller.user;

import com.alibaba.fastjson.JSON;
import com.sun.deploy.net.HttpResponse;
import com.t248.lhd.crm.config.shiro.MyShiroRealm;
import com.t248.lhd.crm.entity.Role;
import com.t248.lhd.crm.entity.User;
import com.t248.lhd.crm.service.IRoleService;
import com.t248.lhd.crm.service.IUserService;
import com.t248.lhd.crm.service.TokenService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class UserController {
    @Resource
    private IUserService userService;
    @Resource
    private IRoleService roleService;
    @Resource
    private TokenService tokenService;

    /**
     * 登陆
     * @param usrName
     * @param usrPassword
     * @param request
     * @return
     */
    @RequestMapping(value = "/dologin")
    public String dologin(String usrName, String usrPassword, HttpServletRequest request, HttpServletResponse response,Map<String,Object> map) throws Exception {
       /* User user=userService.login(usrName,usrPassword);
        if (user!=null){
            String userAgent=request.getHeader("user-agent");
            String token=tokenService.generateToken();
            tokenService.save(token, user);
            Cookie cookie = new Cookie("token_name",token);
            cookie.setPath("/");
            response.addCookie(cookie);
            request.getSession().setAttribute("user",user);
            return "main";
        }else {
            request.setAttribute("message","登陆失败，用户名密码错误！");
            return "login";
        }*/
        System.out.println("Shiro来了来了");
       User user=null;

       try{
           AuthenticationToken token=new UsernamePasswordToken(usrName,usrPassword);

           SecurityUtils.getSubject().login(token);
           user=(User)SecurityUtils.getSubject().getPrincipal();

           request.getSession().setAttribute("user",user);
           String userAgent=request.getHeader("user-agent");
           String token1=tokenService.generateToken();
           tokenService.save(token1, user);
           Cookie cookie = new Cookie("token_name",token1);
           cookie.setPath("/");
           response.addCookie(cookie);
       }catch (IncorrectCredentialsException e){
           e.printStackTrace();
           map.put("message","密码错误");
           return "login";
       }
       catch (Exception e){
           e.printStackTrace();
           map.put("message",e.getMessage());

           return "login";
       }
       return "main";
    }

    /**
     * 注销
     * @param// session
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session,HttpServletRequest request) throws Exception {
        System.out.println("退出");
        Cookie[] cookies = request.getCookies();
        if(cookies.length!=0) {
            List<Cookie> collect = Arrays.stream(cookies).filter(c->"token_name".equals(c.getName())).collect(Collectors.toList());
            if(collect!=null&&collect.size()!=0) {
                String token =	collect.get(0).getValue();
                System.out.println(token);
                tokenService.delete(token);
            }
        }
        System.out.println("退出");
        session.removeAttribute("user");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        return "login";
    }

    @GetMapping(value = "/user/list")
    public String list(String usrName, @RequestParam(required = false,defaultValue = "0") Long roleId,
                       @RequestParam(required = false,defaultValue = "1") int pageIndex,Model model){
        System.out.println("进入list页面");

        Sort sort=Sort.by(Sort.Direction.ASC,"usrId");
        Pageable pageable= PageRequest.of(pageIndex-1,5,sort);
        Page<User> userPage=userService.findUsers(usrName,roleId,pageable);
        model.addAttribute("userPage",userPage);
        model.addAttribute("usrName",usrName);
        model.addAttribute("roleId",roleId);
        List<Role> roles=roleService.findAllRoles();
        model.addAttribute("roles",roles);
        return "/user/list";
    }

    @RequestMapping(value = "/user/add")
    public String add(Model model){
        List<Role> list=roleService.findAllRoles();
        model.addAttribute("roles",list);
        return "/user/add";
    }

    @RequestMapping(value = "/user/save")
    public String save(User user){
        userService.saveUser(user);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/user/edit")
    public String edit(Long usrId,Model model){
        User user=userService.getUser(usrId);
        List<Role> roles=roleService.findAllRoles();
        model.addAttribute("user",user);
        model.addAttribute("roles",roles);
        return "/user/edit";
    }

    @RequestMapping(value = "/user/del")
    @ResponseBody
    public Map del(Long usrId){
        System.out.println(usrId);
        userService.deleteUser(usrId);
        Map map=new HashMap();
        map.put("delResult","true");
        return map;
    }

}
