package com.oymn.tableanalysis.handler;

import com.alibaba.fastjson.JSON;
import com.oymn.tableanalysis.common.StatusCode;
import com.oymn.tableanalysis.dao.exception.ConditionException;
import com.oymn.tableanalysis.dao.pojo.LoginUser;
import com.oymn.tableanalysis.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 登录认证过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        
        //没有携带token或者token值为空串
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        
        //解析token
        Long userId = TokenUtil.verifyToken(token);
        if(userId == null){
            throw new ConditionException(StatusCode.TOKEN_ERROR.getCode(), StatusCode.TOKEN_ERROR.getMsg());
        }
        
        //从redis中获取用户信息
        String redisKey = "TOKEN_" + token;
        String userJson = redisTemplate.opsForValue().get(redisKey);
        if(userJson == null || userJson.length() == 0){
            throw new ConditionException(StatusCode.USER_UNLOGIN.getCode(), StatusCode.USER_UNLOGIN.getMsg());
        }

        LoginUser loginUser = JSON.parseObject(userJson, LoginUser.class);

        if (Objects.isNull(loginUser)) {
            throw new ConditionException(StatusCode.USER_UNLOGIN.getCode(), StatusCode.USER_UNLOGIN.getMsg());
        }

        //存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //放行
        filterChain.doFilter(request, response);
    }
}
