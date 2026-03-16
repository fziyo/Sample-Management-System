package com.fziyo.sms.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Emp {
    private Integer id;
    private String empNo;
    private String name;
    private Integer gender;
    private Integer teamId;
    private Integer roleId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
