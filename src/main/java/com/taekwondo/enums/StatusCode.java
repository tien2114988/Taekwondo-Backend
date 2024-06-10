package com.taekwondo.enums;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum StatusCode {
    SUCCESS(1000, "Success", HttpStatus.OK),

    UNCATEGORIZED(-2000, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(-2001,"Unauthenticated", HttpStatus.UNAUTHORIZED),
    NO_PERMISSION(-2002,"You don't have permission", HttpStatus.FORBIDDEN),
    INVALIDATION(-2003,"Invalid data", HttpStatus.BAD_REQUEST),

    PASSWORD_INVALID(-1000, "Password is invalid", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(-1001, "Username is invalid", HttpStatus.BAD_REQUEST),
    USERNAME_EXISTED(-1002,"Username already exists", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(-1003,"Email already exists", HttpStatus.BAD_REQUEST),

    ;
    private final int code;
    private final String message;
    private final HttpStatusCode httpStatusCode;

    StatusCode(int code, String message, HttpStatusCode httpStatusCode){
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
