package com.t248.lhd.crm.controller.user;

import com.t248.lhd.crm.entity.Right;
import com.t248.lhd.crm.entity.Role;
import com.t248.lhd.crm.entity.SysRoleRight;
import com.t248.lhd.crm.entity.User;
import com.t248.lhd.crm.service.IRoleService;
import com.t248.lhd.crm.service.IUserService;
import com.t248.lhd.crm.service.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RoleController {
    @Resource
    private IRoleService roleService;

    @Resource
    private RightService rightService;
    @Resource
    private IUserService userService;
    @RequestMapping(value = "/role/list")
    public String list(HttpServletRequest request, Model model,String roleName){
        List<Role> roles=roleService.findAllRoles();
        model.addAttribute("roles",roles);
        model.addAttribute("roleName",roleName);
        return "/role/list";
    }
    @RequestMapping(value = "/role/add")
    public String add(Model model){

        return "/role/add";
    }

    @RequestMapping(value = "/role/save")
    public String save(Role role){
        roleService.addRole(role);
        return "redirect:/role/list";
    }
    @RequestMapping(value = "/role/edit")
    public String edit(Long roleId,Model model){
        Role role=roleService.getRole(roleId);

        model.addAttribute("role",role);

        return "/role/edit";
    }
    @RequestMapping(value = "/role/del")
    @ResponseBody
    public Map del(Long roleId){
        roleService.deleteRole(roleId);
        Map map=new HashMap();
        map.put("delResult","true");
        return map;
    }


    @RequestMapping(value = "role/addRole")
    public String addRole(Model model){

        List<Right> folders=rightService.rightsList("Folder");

        model.addAttribute("rights",folders);
        List<Right> document=rightService.rightsList("Document");


        model.addAttribute("document",document);
        return "role/addRole";
    }
    @RequestMapping(value = "role/addRoleSave")
    public String addSaveRole(@RequestParam("rightCode")String[] rightCode,String roleName){
        Role role=new Role();
        role.setRoleName(roleName);
        role.setRoleDesc("爱大米");
        roleService.addRole(role);
        SysRoleRight right=new SysRoleRight();
        Role a=roleService.findByRoleName(roleName);
        for (String r:rightCode
             ) {
            System.out.println(r);
           /* right.setRf_right_code(r.toString());
            right.setRf_role_id(a.getRoleId());
            roleService.addSysRightRole(right);*/
        }
        for (int i=0;i<rightCode.length;i++){
            System.out.println(rightCode[i]);
            roleService.addSysRightRole(rightCode[i],a.getRoleId());
        }
        return "redirect:/role/list";
    }
}
