package com.oymn.tableanalysis.service.impl;

import com.alibaba.fastjson.JSON;

import com.oymn.tableanalysis.common.RoleConstant;
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

    //用于对用户的认证
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //用于对密码的加密
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public String login(User user) throws Exception {
        //使用 authenticationManager 来对用户进行认证

        Authentication authenticate = null;
        UsernamePasswordAuthenticationToken authenticationToken = null;
        try {
            authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            authenticate = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e){
            throw new ConditionException("用户名或密码错误");
        }

        //如果认证没通过
        if(authenticate == null){
            throw new ConditionException("登录认证失败");
        }

        //认证通过，生成token，存入redis中，并返回给前端
        //这里的loginUser是在过滤器阶段的UserDetail接口中，查询数据库是否存在该用户时存进去的
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long userId = loginUser.getUser().getId();
        String token = TokenUtil.generateToken(userId);

        //存入redis中
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(loginUser));

        return token;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        //用户名或密码为空
        if(!StringUtils.hasText(username) || !StringUtils.hasText(password)){
            throw new ConditionException(StatusCode.PARAMS_ERROR.getCode(), StatusCode.PARAMS_ERROR.getMsg());
        }

        //该用户名是否被占用了
        User dbUser = userService.getUserByName(username);
        if(dbUser != null){
            throw new ConditionException("该用户名已经被注册了");
        }

        //使用SpringSecurity的passwordEncoder进行加密
        String newPwd = passwordEncoder.encode(password);
        user.setPassword(newPwd);

        userService.addUser(user);

        //todo: 添加用户
        roleService.setUserRole(user.getId(), RoleConstant.ORDINARY_USER);

    }

    @Override
    public void logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        redisTemplate.delete("TOKEN_" + token);
    }
}
