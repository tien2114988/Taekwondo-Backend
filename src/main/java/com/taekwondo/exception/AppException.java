package com.taekwondo.exception;

import com.taekwondo.enums.StatusCode;
import lombok.Getter;

@Getter
public class AppException extends RuntimeException{
    private StatusCode code;

    public AppException(StatusCode code){
        super(code.getMessage());
        this.code = code;
    }
}
