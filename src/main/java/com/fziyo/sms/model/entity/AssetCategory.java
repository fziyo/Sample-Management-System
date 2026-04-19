package com.fziyo.sms.model.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.concurrent.PriorityBlockingQueue;

@Data
public class AssetCategory {
    private Integer id;
    private String name;
    private String code;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
