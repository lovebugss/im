package com.itrjp.im.common.exception;

public class ForbiddenException extends BizException {
    public ForbiddenException() {
        super(403, "Authentication failed");
    }
}
