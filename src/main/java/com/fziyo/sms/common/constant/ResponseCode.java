package com.fziyo.sms.common.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    
    SUCCESS(200, "Success"),
    PARAM_ERROR(400, "Invalid parameter"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Resource not found"),
    CONFLICT(409, "Data already exists"),
    SYSTEM_ERROR(500, "Internal server error");
    
    private final int code;
    private final String msg;
    
}
