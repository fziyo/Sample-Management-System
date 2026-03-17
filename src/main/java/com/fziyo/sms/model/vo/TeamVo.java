package com.fziyo.sms.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TeamVo {
    private Integer id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
