package com.fziyo.sms.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Team {
    private Integer id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
