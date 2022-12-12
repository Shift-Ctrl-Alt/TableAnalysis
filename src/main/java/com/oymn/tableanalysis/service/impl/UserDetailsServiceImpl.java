package com.oymn.tableanalysis.service.impl;
import com.oymn.tableanalysis.dao.exception.ConditionException;
import com.oymn.tableanalysis.dao.pojo.LoginUser;
import com.oymn.tableanalysis.dao.pojo.User;
import com.oymn.tableanalysis.service.RoleService;
import com.oymn.tableanalysis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 该类实现SpringSecurity中的UserDetailsService接口
 * 其功能是在身份认证时从数据库中查找用户
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //通过用户名查询用户信息
        User user = userService.getUserByName(username);
        
        //如果没有查询到用户就抛出异常
        if(user == null){
            throw new ConditionException("该用户不存在");
        }

        //todo:这里获取权限
        //查询该用户拥有的所有权限
        //List<String> authorities = roleService.getPermissionByUserId(user.getId());
        List<String> authorities = new ArrayList<>();
        
        //把数据封装成UserDetails返回
        return new LoginUser(user, authorities);
        
    }
}
