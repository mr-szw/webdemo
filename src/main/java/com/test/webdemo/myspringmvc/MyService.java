package com.test.webdemo.myspringmvc;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)    //注解保留策略 SOURCE : 源码阶段 ，CLASS : 字节码阶段 ， RUNTIME : 运行时
@Target(ElementType.TYPE)
public @interface MyService {

    String value() default "";

   // RequestMethod method() default {RequestMethod.GET, RequestMethod.POST};
}
