package com.gvgroup.usermanagement.exception;

import lombok.Data;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

    private String responseMessage;
    private ErrorCode errorCode;


    public BaseException(String message, String responseMessage, ErrorCode errorCode) {
        super(message);
        this.responseMessage = responseMessage;
        this.errorCode = errorCode;
    }

}
