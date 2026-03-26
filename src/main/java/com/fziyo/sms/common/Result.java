package com.fziyo.sms.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;
    private Long timestamp;
    
    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }
    
    public static<T> Result<T> success(){
        return new Result<>(200, "success", null);
    }
    
    public static<T> Result<T> success(T data){
        return new Result<>(200, "success", data);
    }
    
    public static<T> Result<T> error(String msg){
        return new Result<>(500, msg, null);
    }
    
    public static<T> Result<T> error(Integer code, String msg){
        return new Result<>(code, msg, null);
    }
}
