package com.fziyo.sms.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
//todo: user request
//todo: role-based access control
public class BorrowRequestCreateDto {
    private Integer assetId;
    private Integer borrowerId; //todo: handled in service from login info
}
