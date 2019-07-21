package com.zjf.halo.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *继承OncePerRequestFilter，
 *确保在一次请求只通过一次filter，而不需要重复执行
 */
public class CorsFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {


        /**
         * ACCESS_CONTROL_ALLOW_ORIGIN：取值范围{*|origin-list}，默认*。*表示接受来自任何源的请求，
         * 而空格分隔的origin-list注意泪出CORS filter允许接受的请求源，
         * 如果请求源不再列表中，将获得HTTP 403 “Forbidden”响应。
         */

        httpServletResponse.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,"*");

        httpServletResponse.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");

        /**
         *ACCESS_CONTROL_ALLOW_METHODS：取值范围{method-list}，默认“GET, POST, HEAD, OPTIONS”。列举所支持的HTTP方法。
         * 该信息将通过“Access-Control-Allow-Methods”头信息返回给调用者，并且需要在service中实现CORS。
         * 非列表内的方法类型的请求将被CORS filters以HTTP 405 “Method not allowed”响应拒绝。
         */

        httpServletResponse.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE, OPTION");

        /**
         * ACCESS_CONTROL_ALLOW_CREDENTIALS：取值{true|false}，默认true。
         * 提示所支持的用户凭据类型，如cookies、HTTP授权或客户端证书。
         * CORS Filter利用该值构造“Access-Control-Allow-Credentials”头信息。
         */

        httpServletResponse.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "false");

        /**
         * “Access-Control-Max-Age”头信息传递给浏览器。
         * 建议浏览器保存预检请求缓存1小时，即该属性值为3600.
         */

        httpServletResponse.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");

        if(CorsUtils.isPreFlightRequest(httpServletRequest)){
            return ;

        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
