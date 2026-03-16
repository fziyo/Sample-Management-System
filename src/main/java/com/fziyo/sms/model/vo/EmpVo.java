package com.fziyo.sms.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmpVo {
    private Integer id;
    private String empNo;
    private String name;
    private Integer gender;
    
    private Integer teamId;
    private String teamName;
    
    private Integer roleId;
    private String roleName;
    
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
