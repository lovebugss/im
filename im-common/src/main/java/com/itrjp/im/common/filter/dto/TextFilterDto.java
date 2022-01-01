package com.itrjp.im.common.filter.dto;

import com.itrjp.im.common.filter.enums.LabelEnum;

public class TextFilterDto {
    public LabelEnum label;
    private boolean ok;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    @Override
    public String toString() {
        return "TextFilterDto{" +
                "ok=" + ok +
                ", label=" + label +
                '}';
    }
}
