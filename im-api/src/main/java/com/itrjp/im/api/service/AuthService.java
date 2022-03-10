package com.itrjp.im.api.service;

import com.itrjp.im.api.enums.TypeEnum;

public interface AuthService<T> {

    boolean isMatch(TypeEnum typeEnum);

    boolean auth(T param);
}
