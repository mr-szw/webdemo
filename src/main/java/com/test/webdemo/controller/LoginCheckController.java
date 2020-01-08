package com.test.webdemo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by Dawei on 2018/7/20.
 */
@RestController
public class LoginCheckController {

    private static final Logger logger = LoggerFactory.getLogger(LoginCheckController.class);

    /* 登陆认证*/
    @PostMapping(value = "/logincheck")
    public String checkLogin(String userName, String passWord) {

        logger.info("checkLogin : userName={}, passWord={}", userName, passWord);

        //获取认证主体
        Subject subject = SecurityUtils.getSubject();
        //编写认证token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, passWord);

        try {
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException e) {
            logger.error("AuthenticationException : e=", e);
            return "校验失败";
        }


        return "登录成功";

    }





}
