package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.websocket.Session;
import java.util.Locale;

@Configuration
@EnableWebMvc
public class WebMvcAutoConfiguration implements WebMvcConfigurer {

    //注入自定义拦截器
    @Autowired
    private myInterceptor myInterceptor;

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor)
                .addPathPatterns("/admin.*")
                .excludePathPatterns("/admin/login");


    }
    //配置静态资源路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
           registry.addResourceHandler("/static/**")
                   .addResourceLocations("classpath:/static/");

    }

    //国际化设置
    @Bean
    public LocaleResolver localeResolver(){
        final SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.CHINA);
        return slr;

    }

    //国际化参数拦截器
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        final LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;

    }
}
