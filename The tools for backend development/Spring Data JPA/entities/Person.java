package com.example.jpademo.demo.entities;


import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

//@Entity 表明该类 (UserEntity) 为一个实体类，它默认对应数据库中的表名是user_entity
@Entity
// 是用于监听实体类添加或者删除操作的。
@EntityListeners(AuditingEntityListener.class)
public class Person {
    /**
     * 在字段或者方法上进行注解@CreatedDate、@CreatedBy、@LastModifiedDate、@LastModifiedBy。
     * 维护数据库的创建时间、创建人、最后修改时间、最后修改人
     */

    @LastModifiedDate

    private String id;





}


