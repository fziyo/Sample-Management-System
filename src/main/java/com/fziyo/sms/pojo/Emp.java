package com.fziyo.sms.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Emp {
    private Integer id;
    private String emp_no;
    private String real_name;
    private String password;
    private Integer gender;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
}
