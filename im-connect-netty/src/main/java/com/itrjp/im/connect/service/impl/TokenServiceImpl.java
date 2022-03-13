package com.itrjp.im.connect.service.impl;

import com.itrjp.im.connect.service.TokenService;
import com.itrjp.im.connect.websocket.RequestParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/13 17:46
 */
@Service
@Slf4j
public class TokenServiceImpl implements TokenService {
    @Override
    public boolean check(RequestParam params) {
        return true;
    }
}
