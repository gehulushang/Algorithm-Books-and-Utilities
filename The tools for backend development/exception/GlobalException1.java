package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;


/**
 * 异常处理方式四，新建全局处理类，在全局异常类中添加一个方法完成异常的同一处理
 * 使用SimpleMappingExceptionResolver处理异常
 * 异常对象可以与视图映射，但是无法传递异常信息
 */
@Configuration
public class GlobalException1 {


    //异常处理方式四，使用SimpleMappingExceptionResolver处理异常
    @Bean

    public SimpleMappingExceptionResolver getSimpleMappingExceptionResolver(){

        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();

        Properties mappings = new Properties();
        //异常对象与视图映射
        mappings.put("java.lang.ArithmeticException", "error1");
        mappings.put("java.lang.NullPointerException","error2");

        //设置异常与视图映射信息的

        resolver.setExceptionMappings(mappings);

        return resolver;

    }



}
