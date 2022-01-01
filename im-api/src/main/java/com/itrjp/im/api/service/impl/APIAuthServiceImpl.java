package com.itrjp.im.api.service.impl;

import com.itrjp.im.api.enums.TypeEnum;
import com.itrjp.im.api.param.BaseAPIParam;
import com.itrjp.im.api.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class APIAuthServiceImpl implements AuthService<BaseAPIParam> {
    @Override
    public boolean isMatch(TypeEnum typeEnum) {
        return typeEnum.equals(TypeEnum.API);
    }

    @Override
    public boolean auth(BaseAPIParam param) {
        return false;
    }
}
