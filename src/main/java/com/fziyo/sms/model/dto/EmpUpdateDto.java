package com.fziyo.sms.model.dto;

import lombok.Data;

@Data
public class EmpUpdateDto {
    private Integer id;
    private String name;
    private Integer gender;
    private Integer teamId;
    private Integer roleId;
}
