package com.oymn.tableanalysis.service;

import com.oymn.tableanalysis.dao.pojo.User;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

    /**
     * 登录接口
     * @param user
     * @return
     * @throws Exception
     */
    String login(User user) throws Exception;

    /**
     * 注册接口
     * @param user
     */
    void register(User user);

    /**
     * 登出接口
     */
    void logout(HttpServletRequest request);
    
}
