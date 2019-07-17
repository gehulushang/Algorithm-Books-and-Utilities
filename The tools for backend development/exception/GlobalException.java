package com.example.demo;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;




/**
 * 异常处理方式三，新建全局处理类，使用@ControllerAdvice和@ExceptionHandler注解处理异常，
 * 将原来在Controller中的方法转移到全局异常处理类中
 */
@ControllerAdvice
public class GlobalException {
    /**

     * java.lang.ArithmeticException

     * 该方法需要返回一个ModelAndView：目的是可以让我们封装异常信息以及视图的指定

     * 参数Exception e:会将产生异常对象注入到方法中

     */

    @ExceptionHandler(value={java.lang.ArithmeticException.class})

    public ModelAndView arithmeticExceptionHandler(Exception e){

        ModelAndView mv = new ModelAndView();

        mv.addObject("error", e.toString());

        mv.setViewName("error1");

        return mv;

    }



    /**

     * java.lang.NullPointerException

     * 该方法需要返回一个ModelAndView：目的是可以让我们封装异常信息以及视图的指定

     * 参数Exception e:会将产生异常对象注入到方法中

     */

    @ExceptionHandler(value={java.lang.NullPointerException.class})

    public ModelAndView nullPointerExceptionHandler(Exception e){

        ModelAndView mv = new ModelAndView();

        mv.addObject("error", e.toString());

        mv.setViewName("error2");

        return mv;

    }

    /*

    //异常处理方式四，使用SimpleMappingExceptionResolver处理异常
    @Bean

    public SimpleMappingExceptionResolver getSimpleMappingExceptionResolver(){

        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();

        Properties mappings = new Properties();

        mappings.put("java.lang.ArithmeticException", "error1");
        mappings.put("java.lang.NullPointerException","error2");

        //设置异常与视图映射信息的

        resolver.setExceptionMappings(mappings);

        return resolver;

    }

     */

}
