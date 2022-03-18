package com.itrjp.im.connect.listener;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;
import com.itrjp.im.connect.handler.NameSpaceHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 鉴权监听器
 *
 * @author renjp
 * @date 2021/12/1 16:44
 */
@Slf4j
@Component
public class IMAuthorizationListener implements AuthorizationListener {
    private final NameSpaceHandler nameSpaceHandler;

    public IMAuthorizationListener(NameSpaceHandler nameSpaceHandler) {
        this.nameSpaceHandler = nameSpaceHandler;
    }

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
        String channel = data.getSingleUrlParam("channel");
        String userId = data.getSingleUrlParam("userId");

        // 先做一个简单的校验
        if (room == null || channel == null || userId == null) {
            return false;
        }
        // 创建频道
        createNameSpace(channel);
        return true;
    }

    /**
     * 创建Namespace
     *
     * @param channel
     */
    private void createNameSpace(String channel) {
        nameSpaceHandler.create(channel);
    }
}
