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
    
    /**
     * 添加类型转换器和格式化器
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldType(LocalDate.class, new USLocalDateFormatter());
    }

    /**
     * 跨域支持
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600 * 24);
    }

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
    
    
    /**
     *  异常处理
     * 重写extendHandlerExceptionResolvers方法，添加异常处理对象
     * 匿名内部类实现
     * @param resolvers
     *
     */
    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new HandlerExceptionResolver() {
            @Nullable
            @Override
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, @Nullable Object handler, Exception ex) {
                String attributeName = Exception.class.getSimpleName().substring(0, 1).toLowerCase() + Exception.class.getSimpleName().substring(1);
                return new ModelAndView("forward:/failure", attributeName, ex);
            }
        });
    }
    
    
    
}
