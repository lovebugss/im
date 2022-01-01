package com.itrjp.im.common.exception;

public class BizException extends IMException {
    private final int code;
    private final String msg;

    public BizException(int code, String msg) {
        super("Biz Exception, code: " + code + ", msg: " + msg);
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
