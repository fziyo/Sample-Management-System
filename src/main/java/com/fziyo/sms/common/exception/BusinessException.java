package com.fziyo.sms.common.exception;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
public class BusinessException extends RuntimeException {
    Integer code;

    public BusinessException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
    public BusinessException(String msg) {
        super(msg);
        this.code = 1;
    }
}
