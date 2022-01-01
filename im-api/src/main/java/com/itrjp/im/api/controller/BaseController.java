package com.itrjp.im.api.controller;

import com.itrjp.im.api.enums.TypeEnum;
import com.itrjp.im.api.param.BaseAPIParam;
import com.itrjp.im.api.service.AuthService;
import org.springframework.beans.factory.ObjectProvider;

public abstract class BaseController {

    private final ObjectProvider<AuthService> authServices;

    protected BaseController(ObjectProvider authServices) {
        this.authServices = authServices;
    }

    protected void auth(TypeEnum typeEnum, BaseAPIParam baseParam) {
        boolean auth = findAuthService(typeEnum)
                .auth(baseParam);

    }

    protected AuthService findAuthService(TypeEnum typeEnum) {
        return this.authServices.stream()
                .filter(authService1 -> authService1.isMatch(typeEnum))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
