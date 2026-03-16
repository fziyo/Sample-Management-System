package com.fziyo.sms.model.dto;

import lombok.Data;

@Data
public class EmpCreateDto {
    private String empNo;
    private String name;
    private Integer gender;
    private Integer teamId;
    private Integer roleId;
}
