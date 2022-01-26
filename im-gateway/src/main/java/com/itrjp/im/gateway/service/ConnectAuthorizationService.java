package com.itrjp.im.gateway.service;

import java.util.List;
import java.util.Map;

/**
 * TODO 鉴权 service
 *
 * @author renjp
 * @date 2022/1/22 10:08
 */
public interface ConnectAuthorizationService {
    boolean auth(Map<String, List<String>> queryParams);
}
