package com.gvgroup.usermanagement.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    GENERAL_ERROR(0, HttpStatus.BAD_REQUEST),
    USER_WITH_USERNAME_EXISTS(-2, HttpStatus.BAD_REQUEST),
    USER_WITH_USER_ID_NOT_FOUND(-3, HttpStatus.NOT_FOUND);

    private final int code;
    private final HttpStatus httpStatus;

    ErrorCode(int code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
