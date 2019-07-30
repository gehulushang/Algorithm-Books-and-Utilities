package com.zjf.security_jwt.model.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户类
 *
 * 创建User类继承了Spring Security的UserDetails接口，
 * 实现Security中相关的安全功能
 *
 */
public class User implements UserDetails {


    private Long id;

    private String password;

    private String username;

    /**
     * CascadeType.REFRESH：级联刷新，当多个用户同时作操作一个实体，
     * 为了用户取到的数据是实时的，在用实体中的数据之前就可以调用一下refresh()方法！
     *
     * FetchType.EAGER：急加载
     */
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * 权限集合
     *GrantedAuthority：对认证主题的应用层面的授权，含当前用户的权限信息，
     * 通常使用角色表示
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
