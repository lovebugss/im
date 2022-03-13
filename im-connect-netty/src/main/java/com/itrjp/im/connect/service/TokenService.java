package com.itrjp.im.connect.service;

import com.itrjp.im.connect.websocket.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/13 16:47
 */
public interface TokenService {
    boolean check(RequestParam params);
}
