package com.itrjp.im.server.listener;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * 鉴权监听器
 *
 * @author renjp
 * @date 2021/12/1 16:44
 */
@Slf4j
public class IMAuthorizationListener implements AuthorizationListener {
    /**
     * 鉴权
     *
     * @param data
     * @return
     */
    @Override
    public boolean isAuthorized(HandshakeData data) {
        log.info("isAuthorized...");
        Map<String, List<String>> urlParams = data.getUrlParams();
        log.info("url params: {}", urlParams);
        String room = data.getSingleUrlParam("room");

        if (room == null) {
            return false;
        }
        return true;
    }
}
