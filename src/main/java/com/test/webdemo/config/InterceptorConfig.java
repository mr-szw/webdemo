package com.test.webdemo.config;

import com.test.webdemo.interceptor.CommonInterceptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author by Dawei on 2018/6/3.
 */
@ContextConfiguration
public class InterceptorConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new CommonInterceptor());
    }
}
