package com.fziyo.sms.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BorrowRequest {
    private Integer id;
    private Integer assetId;
    private Integer borrowerId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer status;
    private Integer approverId;
    private LocalDateTime approveTime;
}
