package com.fziyo.sms.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
//todo: role-based access control
public class BorrowRequestCreateDto {
    private Integer assetId;
}
