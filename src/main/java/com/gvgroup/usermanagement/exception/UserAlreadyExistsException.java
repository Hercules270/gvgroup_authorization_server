package com.gvgroup.usermanagement.exception;

import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends BaseException{

    private ErrorCode errorCode;

    public UserAlreadyExistsException(String message) {
        super(message);
        this.errorCode = ErrorCode.GENERAL_ERROR;
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = ErrorCode.GENERAL_ERROR;
    }

    public UserAlreadyExistsException(String message, String responseMessage) {
        super(message, responseMessage);
        this.errorCode = ErrorCode.GENERAL_ERROR;
    }

    public UserAlreadyExistsException(String message, String responseMessage, ErrorCode errorCode) {
        super(message, responseMessage);
        this.errorCode = errorCode;
    }

}
