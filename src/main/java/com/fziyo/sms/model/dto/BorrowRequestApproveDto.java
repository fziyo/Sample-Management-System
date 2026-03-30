package com.fziyo.sms.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
//todo: admin
//todo: role-based access control
public class BorrowRequestApproveDto {
    private Integer id;
    private Integer status;
    
    private Integer requestApproverId;
    private LocalDateTime requestApproveTime;
    
    private LocalDateTime borrowStartTime;
    private LocalDateTime returnRequestTime;
    
    private Integer returnApproverId;
    private LocalDateTime returnApproveTime;
}
