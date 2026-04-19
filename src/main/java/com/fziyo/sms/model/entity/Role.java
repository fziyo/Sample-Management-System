package com.fziyo.sms.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Role {
    private Integer id;
    private String roleName;
    private String roleCode;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
