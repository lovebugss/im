package com.itrjp.im.common.base;

public class BaseParam {
    protected String appId;
    protected String sign;
    protected Long signedAt;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Long getSignedAt() {
        return signedAt;
    }

    public void setSignedAt(Long signedAt) {
        this.signedAt = signedAt;
    }
}
