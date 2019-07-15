package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

//自定义拦截器

/**
 * @Component把普通pojo实例化到spring容器中
 * 泛指各种组件，就是说当我们的类不属于各种归类的时候
 * （不属于@Controller、@Services等的时候），
 * 我们就可以使用@Component来标注这个类
 */
@Component
public class myInterceptor implements HandlerInterceptor{


}
