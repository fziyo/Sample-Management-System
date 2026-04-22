package com.fziyo.sms.model.vo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CategoryVo {
    private Integer id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
