package com.itrjp.im.common.exception;

public class IMException extends RuntimeException {
    public IMException(String message) {
        super(message);
    }

    public IMException() {
        super("IM Exception");
    }
}
