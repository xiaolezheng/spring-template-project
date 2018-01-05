package com.xxx.xtable.api.support;

public class ClientNotInitException extends RuntimeException {
    public ClientNotInitException() {
        super();
    }

    public ClientNotInitException(String message) {
        super(message);
    }

    public ClientNotInitException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientNotInitException(Throwable cause) {
        super(cause);
    }
}
