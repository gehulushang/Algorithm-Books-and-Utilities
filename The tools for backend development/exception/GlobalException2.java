package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;


/**
 * 异常处理方式五，新建全局处理类，引用HandlerExceptionResolver接口
 * 重写resolveException处理异常
 *
 */
@Configuration
public class GlobalException2 implements HandlerExceptionResolver {


    @Override

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,

                                         Exception ex) {

        ModelAndView mv = new ModelAndView();

        //判断不同异常类型，做不同视图跳转

        if(ex instanceof ArithmeticException){

            mv.setViewName("error1");

        }



        if(ex instanceof NullPointerException){

            mv.setViewName("error2");

        }

        mv.addObject("error", ex.toString());

        return mv;

    }
}