package com.fziyo.sms.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BorrowRequest {
    private Integer id;
    private Integer assetId;
    private Integer borrowerId;
    private Integer status;
    
    private Integer requestHandlerId;
    private LocalDateTime requestApproveTime;
    
    private LocalDateTime borrowStartTime;
    private LocalDateTime returnRequestTime;
    
    private Integer returnHandlerId;
    private LocalDateTime returnApproveTime;
    
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
