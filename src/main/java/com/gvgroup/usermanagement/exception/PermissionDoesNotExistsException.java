package com.gvgroup.usermanagement.exception;

public class PermissionDoesNotExistsException extends BaseException {

    public PermissionDoesNotExistsException(String message, String responseMessage, ErrorCode errorCode) {
        super(message, responseMessage, errorCode);
    }
}
