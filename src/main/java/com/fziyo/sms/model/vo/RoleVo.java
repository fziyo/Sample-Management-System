package com.fziyo.sms.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoleVo {
    private int id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
