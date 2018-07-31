package com.test.webdemo.services.impl;

import com.test.webdemo.pojo.DemoPojo;
import com.test.webdemo.services.UserInfoService;
import com.test.webdemo.utils.UniqueIDUtil;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author by Dawei on 2018/7/25.
 */
public class UserInfoServiceImpl implements UserInfoService{


   private static DemoPojo demoPojo;

   private static Set<String> soleSet;

    static {
        demoPojo.setuId(UniqueIDUtil.getUniqueID());
        demoPojo.setPassWord("Dawei");
        demoPojo.setBirthday(new Date());
        demoPojo.setUseName("Dawei");
        demoPojo.setPhoneNum("15554485117");
        demoPojo.setRoleSet(soleSet);
    }



    /**
     * 获取用户信息通过手机号
     *
     * @param phoneNum 手机号
     * @return 用户信息实体
     */
    @Override
    public DemoPojo getUserPojoByPhone(String phoneNum) {
        return new DemoPojo();
    }

    @Override
    public Set<String> getUserRolesBy(String phoneNum) {
        return null;
    }

    @Override
    public Set<String> getAuthsByRole(String role) {
        return null;
    }

    @Override
    public Set<String> getAuthsByRoles(List<String> roleList) {
        return null;
    }
}
