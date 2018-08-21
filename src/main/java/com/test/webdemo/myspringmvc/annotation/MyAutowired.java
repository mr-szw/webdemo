package com.test.webdemo.myspringmvc.annotation;


import java.lang.annotation.*;

/**
 * @author by Dawei on 2018/8/21.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)    //注解保留策略 SOURCE : 源码阶段 ，CLASS : 字节码阶段 ， RUNTIME : 运行时
@Target(ElementType.FIELD)    //注解作用范围  TYPE : 类上
public @interface MyAutowired {
    String  value() default "";
}
