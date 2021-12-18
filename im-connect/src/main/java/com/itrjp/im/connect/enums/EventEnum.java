package com.itrjp.im.connect.enums;

public enum EventEnum {
    MESSAGE("message");
    private String code;

    EventEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}