package com.fziyo.sms.common.exception;

import com.fziyo.sms.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e) {
        log.error("Business Error {}", e.getMessage());
        return Result.error(e.getCode(), e.getMessage());
    }
    @ExceptionHandler(DuplicateKeyException.class)
    public Result<?> handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("Duplicate Key Error {}", e.getMessage());
        return Result.error("Duplicate Key Error");
    }
    
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        log.error("Error {}", e.getMessage());
        return Result.error(500, e.getMessage());
    }
}
