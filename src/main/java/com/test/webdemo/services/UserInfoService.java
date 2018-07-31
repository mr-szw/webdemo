package com.test.webdemo.services;

import com.test.webdemo.pojo.DemoPojo;

import java.util.List;
import java.util.Set;

/**
 * @author by Dawei on 2018/7/25.
 */
public interface UserInfoService {

    /**
     * 获取用户信息通过手机号
     * @param phoneNum 手机号
     * @return 用户信息实体
     */
    DemoPojo getUserPojoByPhone(String phoneNum);


    Set<String> getUserRolesBy(String phoneNum);

    Set<String> getAuthsByRole(String role);

    Set<String> getAuthsByRoles(List<String> roleList);

}
