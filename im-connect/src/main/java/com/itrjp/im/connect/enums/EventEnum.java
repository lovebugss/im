package com.itrjp.im.connect.enums;

import java.util.Locale;

public enum EventEnum {
    MESSAGE("message"),
    NOTICE("notice");
    private final String code;

    EventEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return this.getCode().toLowerCase(Locale.ROOT);
    }
}
