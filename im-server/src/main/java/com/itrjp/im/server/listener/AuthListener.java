package com.itrjp.im.server.listener;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;

/**
 * 鉴权监听器
 *
 * @author renjp
 * @date 2021/12/1 16:44
 */
public class AuthListener implements AuthorizationListener {
    /**
     * 鉴权
     *
     * @param data
     * @return
     */
    @Override
    public boolean isAuthorized(HandshakeData data) {

        return true;
    }
}
