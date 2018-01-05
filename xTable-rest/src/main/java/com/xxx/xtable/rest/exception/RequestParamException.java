package com.xxx.xtable.rest.exception;


public class RequestParamException extends RuntimeException {
    public RequestParamException() {
        super();
    }

    public RequestParamException(String message) {
        super(message);
    }

    public RequestParamException(String message, Throwable cause) {
        super(message, cause);
    }
}
