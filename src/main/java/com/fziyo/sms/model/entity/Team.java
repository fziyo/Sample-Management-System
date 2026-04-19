package com.fziyo.sms.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Team {
    private Integer id;
    private String teamName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private LocalDateTime updatedTime;
    private Integer updatedBy;
}
