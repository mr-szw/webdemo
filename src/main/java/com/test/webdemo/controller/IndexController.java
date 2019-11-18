package com.test.webdemo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author by Dawei on 2018/5/30.
 */
@RestController
@RequestMapping(value = "/lottery")
public class IndexController {

    /**
     *
     * @return
     */
    @GetMapping(value = "/index")
    public String index() {
        System.out.println("index.>>>>>>>>>>>>>>>>>>>>>>>>");
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            String ip= inetAddress.getHostAddress(); //获取本机ip
            String hostName= inetAddress.getHostName(); //获取本机计算机名称
            return "ip=" + ip + "  hostName=" + hostName + "22";
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "not get ip";
    }
}
