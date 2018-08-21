package com.test.webdemo.myspringmvc.annotation;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface MyRequestMapping {

    String value() default "";
}
