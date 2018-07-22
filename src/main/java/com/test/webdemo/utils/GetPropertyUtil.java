package com.test.webdemo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author by Dawei on 2018/7/22
 */
public class GetPropertyUtil {

    private static final Logger logger = LoggerFactory.getLogger(GetPropertyUtil.class);


    private static  Properties property;

    /**
     * 通过文件的路径获取配置文件信息
     * @param pathName psth
     * @return Properties
     */
    public static Properties getPropertiesByFileClassPath(String pathName) {
        property = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream inputStream = GetPropertyUtil.class.getClassLoader().getResourceAsStream(pathName);
        //使用properties对象加载数据流
        try {
            property.load(inputStream);
        } catch (IOException e) {
            logger.error(" Exception occur , e={}", e);
            property = null;
        }
        return property;
    }

    /**
     * 直接获取配置信息
     * @param pathName 配置信息位置
     * @param keyStr 信息key
     * @return 配置信息值
     */
    public static String getPropertiesInfoString(String pathName, String keyStr, String defult) {
        Properties propertiesByFileClassPath = getPropertiesByFileClassPath(pathName);
        String property = propertiesByFileClassPath.getProperty(keyStr);
        if(!StringUtils.isEmpty(property)) {
            return property;
        } else {
            return defult;
        }
    }

    /**
     * 直接获取配置信息
     * @param pathName 配置信息位置
     * @param keyStr 信息key
     * @return 配置信息值
     */
    public static Integer getPropertiesInfoInt(String pathName, String keyStr, String defult) {
        Properties propertiesByFileClassPath = getPropertiesByFileClassPath(pathName);
        String property = propertiesByFileClassPath.getProperty(keyStr);
        if(!StringUtils.isEmpty(property)) {
            return new Integer(property);
        }else {
            return new Integer(defult);
        }
    }

}
