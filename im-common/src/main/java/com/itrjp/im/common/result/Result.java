package com.itrjp.im.common.result;

import com.itrjp.im.common.exception.BizException;

public class Result<T> {

    private int code;
    private String msg;
    private T data;

    private Result(int code, String message, T data) {
        this.code = code;
        this.msg = message;
    }

    private Result(ErrorCode errorCode, T data) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMessage();
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ErrorCode.SUCCESS, data);
    }

    public static <T> Result<T> error() {
        return new Result<>(ErrorCode.ERROR, null);
    }


    public static <T> Result<T> error(BizException exception) {
        return new Result<>(exception.getCode(), exception.getMsg(), null);
    }

    public static <T> Result<T> error(ErrorCode errorCode) {
        return new Result<>(errorCode.getCode(), errorCode.getMessage(), null);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
