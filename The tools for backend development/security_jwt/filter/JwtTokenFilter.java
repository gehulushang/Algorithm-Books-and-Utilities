package com.zjf.security_jwt.filter;

import com.zjf.security_jwt.comm.Const;
import com.zjf.security_jwt.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * 创建Token过滤器，用于每次外部对接口请求时的Token处理
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String authHeader = request.getHeader(Const.HEADER_STRING);

            if(authHeader != null && authHeader.startsWith(Const.TOKEN_PREFIX)){

                final String authToken = authHeader.substring(Const.TOKEN_PREFIX.length());
                String username = jwtTokenUtil.getUsernameFromToken(authToken);

                                   //SecurityContextHolder：提供对SecurityContext的访问
                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                    if(jwtTokenUtil.validateToken(authToken,userDetails)){
                        /**
                         * UsernamePasswordAuthenticationToken
                         * 继承AbstractAuthenticationToken
                         * 实现Authentication
                         *
                         * 在页面中输入用户名和密码之后
                         * 首先会进入到UsernamePasswordAuthenticationToken验证(Authentication)，
                         * 然后生成的Authentication会被交由AuthenticationManager来进行管理
                         */
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails,null,userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        chain.doFilter(request, response);
    }
}

















