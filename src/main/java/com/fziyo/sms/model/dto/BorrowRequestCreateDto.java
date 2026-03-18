package com.fziyo.sms.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BorrowRequestCreateDto {
    private Integer assetId;
    private Integer borrowId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer status;
    private Integer approverId;
    private LocalDateTime approveTime;
}
