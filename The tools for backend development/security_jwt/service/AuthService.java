package com.zjf.security_jwt.service;

import com.zjf.security_jwt.model.entity.User;

public interface AuthService {

    User register( User userToAdd );
    String login( String username, String password );
}
