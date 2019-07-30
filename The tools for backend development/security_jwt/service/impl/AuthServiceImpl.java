package com.zjf.security_jwt.service.impl;


import com.zjf.security_jwt.model.entity.User;
import com.zjf.security_jwt.repository.UserRepository;
import com.zjf.security_jwt.service.AuthService;
import com.zjf.security_jwt.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;


    //注册
    @Override
    public User register(User userToAdd) {
        final String username = userToAdd.getUsername();
        if( userRepository.findByUsername(username)!=null ) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword( encoder.encode(rawPassword) );
        return userRepository.save(userToAdd);
    }
     //登录
    @Override
    public String login(String username, String password) {
        /**
         * 在页面中输入用户名和密码之后
         * 首先会进入到UsernamePasswordAuthenticationToken验证(Authentication)，
         * 然后生成的Authentication会被交由AuthenticationManager来进行管理
         */

        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken( username, password );

        /**
         *AuthenticationManager通UserDetailsService和UserDetail来返回一个
         * 以UsernamePasswordAuthenticationToken实现的带用户名和密码以及权限的Authentication
         */
        final Authentication authentication = authenticationManager.authenticate(upToken);

        //SecurityContextHolder：提供对SecurityContext的访问
        SecurityContextHolder.getContext().setAuthentication(authentication);

        /**
         * UserDetails：构建Authentication对象必须的信息，可以自定义，可能需要访问DB得到
         * UserDetailsService：通过username构建UserDetails对象，
         * 通过loadUserByUsername根据username获取UserDetail对象
         */
        final UserDetails userDetails = userDetailsService.loadUserByUsername( username );
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }
}
