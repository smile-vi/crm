package com.t248.lhd.crm.config.shiro;

import com.t248.lhd.crm.config.MyByteSource;
import com.t248.lhd.crm.entity.Right;
import com.t248.lhd.crm.entity.Role;
import com.t248.lhd.crm.entity.User;
import com.t248.lhd.crm.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {

    

    @Resource
    private IUserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限认证!!!");
        /*User u=new User();
        Object o=principalCollection.getPrimaryPrincipal();
        if (o instanceof User){
            u=(User)o;
        }else{
           BeanUtils.copyProperties(o,u);
        }*/
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        /*authorizationInfo.addStringPermission("用户管理");*/

        User user=(User)principalCollection.getPrimaryPrincipal();
        for (Right right:user.getRole().getRights()
             ) {
            System.out.println("用户的权限:"+right.getRightText());
            authorizationInfo.addStringPermission(right.getRightText());
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("身份认证:MyShrioRealm.goGetAuthenticationInfo()");
        //获取用户输入的账号
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        String usrName=token.getUsername();
        String usrPassword=new String(token.getPassword());
        System.out.println("usrName:"+usrName);
        System.out.println("usrPassword:"+usrPassword);
        User user=userService.getUser(usrName);
        System.out.println("------>>user="+user);
        if (user==null){
            throw new UnknownAccountException("账号不存在!");
        }/*else if(!user.getUsrPassword().equals(usrPassword)){
            throw new IncorrectCredentialsException("密码不正确!");
        }*/
        //认证成功，给用户添加权限
        Role role=userService.getRoleByUser(user);
        List<Right> rights=userService.findRightsByRole(role);
        role.getRights().addAll(rights);
        user.setRole(role);
        //认证信息添加盐


        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(
                user,//用户
                user.getUsrPassword(),
                new MyByteSource("salt"),
                getName()//realm name
        );
        return authenticationInfo;
    }
    /**
     * 自定义方法：清除所有的  认证缓存  和 授权缓存
     */
   /* public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
    *//**
     * 自定义方法：清除所有 授权缓存
     *//*
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    *//**
     * 自定义方法：清除所有 认证缓存
     *//*
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }*/



}
