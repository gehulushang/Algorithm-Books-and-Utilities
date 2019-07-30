package com.zjf.security_jwt.repository;

import com.zjf.security_jwt.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername( String username );
}
