package com.itrjp.im.api.service.impl;

import com.itrjp.im.api.enums.TypeEnum;
import com.itrjp.im.api.param.BaseSDKParam;
import com.itrjp.im.api.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class SDKAuthServiceImpl implements AuthService<BaseSDKParam> {
    @Override
    public boolean auth(BaseSDKParam param) {
        return false;
    }

    @Override
    public boolean isMatch(TypeEnum typeEnum) {
        return typeEnum.equals(TypeEnum.SDK);
    }
}