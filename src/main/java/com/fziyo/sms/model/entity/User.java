package com.fziyo.sms.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Integer id;
    private String username;
    private String pwd;
    private String name;
    private String tel;
    private String email;
    private Integer gender;
    private Integer accountNonExpired;
    private Integer credentialsNonExpired;
    private Integer accountNonLocked;
    private Integer accountEnabled;
    private LocalDateTime createTime;
    private Integer createBy;
    private LocalDateTime editTime;
    private Integer editedBy;
    private LocalDateTime lastLoginTime;
    
    private String teamName;
    private String roleName;
}
