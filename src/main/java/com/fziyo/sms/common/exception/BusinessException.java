package com.fziyo.sms.common.exception;


import com.fziyo.sms.common.constant.ResponseCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
public class BusinessException extends RuntimeException {
    private final Integer code;

    public BusinessException(ResponseCode responseCode) {
        super(responseCode.getMsg());
        this.code = responseCode.getCode();
    }
    
    public BusinessException(ResponseCode errorCode, String msg) {
        super(msg);
        this.code = errorCode.getCode();
    }
}
