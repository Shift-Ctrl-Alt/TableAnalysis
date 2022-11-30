package com.oymn.tableanalysis.service.impl;

import com.alibaba.fastjson.JSON;

import com.oymn.tableanalysis.common.StatusCode;
import com.oymn.tableanalysis.dao.exception.ConditionException;
import com.oymn.tableanalysis.dao.pojo.LoginUser;
import com.oymn.tableanalysis.dao.pojo.User;
import com.oymn.tableanalysis.service.LoginService;
import com.oymn.tableanalysis.service.RoleService;
import com.oymn.tableanalysis.service.UserService;
import com.oymn.tableanalysis.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {
    

    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;


    @Override
    public String login(User user) throws Exception {
        return null;
    }

    @Override
    public void register(User user) {

    }

    @Override
    public void logout(HttpServletRequest request) {

    }
}
