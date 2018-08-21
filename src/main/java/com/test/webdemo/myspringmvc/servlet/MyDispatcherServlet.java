package com.test.webdemo.myspringmvc.servlet;

import com.test.webdemo.myspringmvc.MyService;
import com.test.webdemo.myspringmvc.annotation.MyAutowired;
import com.test.webdemo.myspringmvc.annotation.MyController;
import com.test.webdemo.myspringmvc.annotation.MyRequestMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by Dawei on 2018/8/21
 */
public class MyDispatcherServlet extends HttpServlet {

    private Map<String, Object> instanceMap = new HashMap<>();
    private Map<String, Object> handlerMapping = new HashMap<>();
    private List<String> clazzNames = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("Do MyDispatcherServlet init ......");
        super.init(config);

        String packagePath = "com.test";
        //1、进行包扫描
        try {
            doScanPackage(packagePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //2、获取注解
        try {
            getAnnotationInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //3、依赖注入
        try {
            injection();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //4、URL 与 方法映射
        try {
            handlerMethodMapping();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


    /**
     * 实现包扫描
     * @param packagePath 包路径
     */
    private void doScanPackage(String packagePath) throws Exception{
        //将url路径转化为 文件路径
        String classPath = packagePath.replaceAll("\\.", "/");
        //获取指定文件下的 文件URL
        URL resource = this.getClass().getClassLoader().getResource("/" + classPath);
        if(resource != null) {
            //指定路径下的文件
            File[] files = new File(resource.getFile()).listFiles();
            //递归获取 类
            if(files != null && files.length > 0) {
                for(File file : files) {
                    if(file.isDirectory()) {
                        doScanPackage(packagePath + "." + file.getName());
                    } else {
                        clazzNames.add(packagePath + "." + file.getName().replace(".class", ""));
                        System.out.println( "Scan package : " + packagePath + "." + file.getName());
                    }
                }
            }
        } else {
            throw new Exception("Scan package path is not true directory, Error packagePath : " + packagePath);
        }
    }


    /* 获取 注解 与类的映射 */
    private void getAnnotationInfo() throws Exception{
        if (clazzNames.isEmpty()) {
            System.out.println("Scan package Name `s class is empty  ...");
            return;
        }

        for(String className : clazzNames) {
            Class<?> clazz = Class.forName(className);
            //实例化一个类
            Object instance = clazz.newInstance();

            //判断是否是Controller 注解
            if(clazz.isAnnotationPresent(MyController.class)) {
                //实例化一个类
                MyRequestMapping annotation = clazz.getAnnotation(MyRequestMapping.class);
                //配置的路径
                String value = annotation.value();
                //路径与类的映射
                instanceMap.put(value, instance);
            }
            //判断是否是Service注解
            if(clazz.isAnnotationPresent(MyService.class))  {
                MyService annotation = clazz.getAnnotation(MyService.class);
                //配置的路径
                String value = annotation.value();
                // 别名 与类的映射
                instanceMap.put(value, instance);
            }
        }
    }

    /* 实现 注入 */
    private void injection() throws IllegalAccessException {
        if (instanceMap.isEmpty()) {
            System.out.println("Scan package Name `s class is empty  ...");
            return;
        }

        //遍历所以后类 便利所有Autowired
        for(Map.Entry<String, Object> entry : instanceMap.entrySet()) {
            //去除对象
            Object instance = entry.getValue();
            //获取所有属性字段
            Field[] declaredFields = instance.getClass().getDeclaredFields();
            //判断所有含有myAutowired的属性
            if(declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    if(field.isAnnotationPresent(MyAutowired.class)) {
                        //获取到注解
                        MyAutowired annotation = field.getAnnotation(MyAutowired.class);
                        //获取注解值
                        String value = annotation.value();
                        //实现注入过程 开放权限
                        field.setAccessible(true);
                        field.set(instance, instanceMap.get(value));
                    }

                }
            }
        }
    }

    /* 获取方法 映射*/
    private void handlerMethodMapping() throws IllegalAccessException {
        if (instanceMap.isEmpty()) {
            System.out.println("Scan package Name `s class is empty  ...");
            return;
        }

        //遍历所以后类 便利所有Autowired
        for(Map.Entry<String, Object> entry : instanceMap.entrySet()) {
            //取出对象
            Object instance = entry.getValue();

            //映射路径
            StringBuilder mappingPath = new StringBuilder();
            //获取类上RequestMapping的值
            if(instance.getClass().isAnnotationPresent(MyRequestMapping.class)) {
                MyRequestMapping annotation = instance.getClass().getAnnotation(MyRequestMapping.class);
                mappingPath.append(annotation.value());
            }
            Method[] declaredMethods = instance.getClass().getDeclaredMethods();
            //获取方法上的映射地址
            if(declaredMethods != null && declaredMethods.length > 0) {
                for (Method method : declaredMethods) {
                    if(method.isAnnotationPresent(MyRequestMapping.class)) {
                        //获取到注解
                        MyRequestMapping annotation = method.getAnnotation(MyRequestMapping.class);
                        //获取注解值
                        mappingPath.append(annotation.value());
                        //实现注入过程 开放权限
                        handlerMapping.put(mappingPath.toString(), method);
                    }
                }
            }
        }

        }

}
