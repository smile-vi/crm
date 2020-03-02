package com.t248.lhd.crm.service.impl;

import com.t248.lhd.crm.entity.Right;
import com.t248.lhd.crm.entity.Role;
import com.t248.lhd.crm.entity.User;
import com.t248.lhd.crm.repository.RightRepository;
import com.t248.lhd.crm.repository.RoleRepository;
import com.t248.lhd.crm.repository.UserRepository;
import com.t248.lhd.crm.service.IUserService;
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
public class IUserServiceImpl implements IUserService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private RoleRepository roleRepository;
    @Resource
    private RightRepository rightRepository;
    @Override
    public User login(String usrName, String usrPassword) {
        return userRepository.findByUsrNameAndUsrPassword(usrName,usrPassword);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long usrId) {
        userRepository.deleteUser(usrId);
    }

    @Override
    public User getUser(Long usrId) {
        return userRepository.getOne(usrId);
    }

    @Override
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findUsers(String usrName, Long roleId, Pageable pageable) {
        Specification specification=new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                if (usrName!=null&&!usrName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("usrName"),"%"+usrName+"%"));
                }
                if (roleId!=null&&roleId.longValue()!=0l){
                    Role role=new Role();
                    role.setRoleId(roleId);
                    predicates.add(criteriaBuilder.equal(root.get("role"),role));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return userRepository.findAll(specification,pageable);
    }

    @Override
    public User getUser(String usrName) {
        return userRepository.findUserByUsrName(usrName);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleByUser(User user) {
        return roleRepository.findRoleByUsers(user);
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<Right> findAllRights() {
        return rightRepository.findAll();
    }

    @Override
    public List<Role> findRoles(String roleName) {
        List<Role> list=null;
        if (roleName!=null){
            list=roleRepository.findRolesByRoleNameLike("%"+roleName+"%");
        }else {
            list=roleRepository.findAll();
        }
        return list;
    }

    @Override
    public List<Right> findRightsByRole(Role role) {
        return rightRepository.findRightsByRoles(role);
    }
}
