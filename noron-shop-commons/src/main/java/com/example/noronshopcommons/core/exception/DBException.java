package com.example.noronshopcommons.core.exception;

import lombok.Getter;

@Getter
public class DBException extends RuntimeException {
    static final long serialVersionUID = 1651914954614L;
    private int code = 400;

    public DBException() {
    }

    public DBException(String message) {
        super(message);
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBException(Throwable cause) {
        super(cause);
    }

    protected DBException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
