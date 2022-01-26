package com.itrjp.im.gateway.service.impl;

import com.itrjp.im.gateway.service.ConnectAuthorizationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/1/22 10:09
 */
@Service
public class ConnectAuthorizationServiceImpl implements ConnectAuthorizationService {
    @Override
    public boolean auth(Map<String, List<String>> queryParams) {
        return false;
    }
}
