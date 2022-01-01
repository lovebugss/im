package com.itrjp.im.common.filter.param;

import com.itrjp.im.common.base.BaseParam;

public class TextFilterParam extends BaseParam {

    public String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TextFilterParam{" +
                "appId='" + getAppId() + '\'' +
                ", sign='" + getSign() + '\'' +
                ", signedAt=" + getSignedAt() +
                ", content='" + getContent() + '\'' +
                '}';
    }
}
