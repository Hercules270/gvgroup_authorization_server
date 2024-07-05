package com.gvgroup.usermanagement.exception;

import lombok.Data;

@Data
public class BaseException extends RuntimeException{

    private String responseMessage;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message, String responseMessage) {
        super(message);
        this.responseMessage = responseMessage;
    }

}
