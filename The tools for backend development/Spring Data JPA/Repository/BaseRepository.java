package com.example.jpademo.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


// 新建BaseRepository接口继承JpaRepository接口，
//JpaRepository中封装了一些数据库方法
//而这个新建的BaseRepository用于适应本项目的需要

public interface BaseRepository extends JpaRepository {



}
