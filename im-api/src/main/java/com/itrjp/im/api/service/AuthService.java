package com.itrjp.im.api.service;

import com.itrjp.im.api.enums.TypeEnum;
import com.itrjp.im.api.param.BaseParam;

public interface AuthService<T> {

    boolean isMatch(TypeEnum typeEnum);

    boolean auth(T param);
}
