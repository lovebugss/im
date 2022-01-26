package com.itrjp.im.common.result;

/**
 * 错误代码
 *
 * @author renjp
 * @date 2022/1/22 10:58
 */
public enum ErrorCode {
    SUCCESS(200, "success"),
    ERROR(0, "error"),
    UNAUTHORIZED(403, "鉴权失败");
    /**
     * 错误码
     */
    private int code;
    /**
     * 错误信息
     */
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
