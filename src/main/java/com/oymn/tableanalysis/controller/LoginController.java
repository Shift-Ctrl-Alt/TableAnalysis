package com.oymn.tableanalysis.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.oymn.tableanalysis.common.StatusCode;
import com.oymn.tableanalysis.dao.exception.ConditionException;
import com.oymn.tableanalysis.dao.pojo.User;
import com.oymn.tableanalysis.service.LoginService;
import com.oymn.tableanalysis.service.UserService;
import com.oymn.tableanalysis.utils.RSAUtil;
import com.oymn.tableanalysis.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Api("登录注册的接口")
@RestController
@RequestMapping("/user")
public class LoginController {
    
    @Autowired
    private LoginService loginService;
    
    @Autowired
    private UserService userService;


    @ApiOperation("判断token是否过期")
    @PostMapping("/token/check")
    public Result<String> checkTokenStatus(String token){
        try {
            Algorithm algorithm = Algorithm.RSA256(RSAUtil.getPublicKey(), RSAUtil.getPrivateKey());

            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            Date expireTime = jwt.getExpiresAt();
            
            System.out.println(expireTime);
            
            //token过期
            System.out.println(new Date());
            System.out.println(expireTime.compareTo(new Date()));
            if(expireTime.compareTo(new Date()) < 0){
                return Result.success("201");
            }else{
                return Result.success("200");
            }
            
        } catch (TokenExpiredException e) {
            //throw new ConditionException(StatusCode.TOKEN_EXPIRED.getCode(), StatusCode.TOKEN_EXPIRED.getMsg());
            return Result.success("201");
        }catch (Exception e){
            throw new ConditionException(StatusCode.TOKEN_ERROR.getCode(), StatusCode.TOKEN_ERROR.getMsg());
        }
    }

    /**
     * 登录接口
     * @param user 
     * @return
     */
    @ApiOperation("登录")
    @PostMapping("login")
    public Result<String> login(@RequestBody User user) throws Exception {
        String token = loginService.login(user);
        return Result.success(token);
    }

    /**
     * 注册接口
     * @param user
     * @return
     */
    @ApiOperation("注册")
    @PostMapping("register")
    public Result register(@RequestBody User user){
        loginService.register(user);
        return Result.success();
    }

    /**
     * 登出接口
     * @param request
     * @return
     */
    @ApiOperation("登出")
    @PostMapping("logout")
    public Result logout(HttpServletRequest request){
        loginService.logout(request);
        return Result.success();
    }
}
