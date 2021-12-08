package com.itrjp.im.server.listener;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIONamespace;
import com.itrjp.im.server.cache.NameSpaceCache;
import com.itrjp.im.server.handler.NameSpaceHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 鉴权监听器
 *
 * @author renjp
 * @date 2021/12/1 16:44
 */
@Slf4j
@Component
public class IMAuthorizationListener implements AuthorizationListener {
    private final NameSpaceCache nameSpaceCache = NameSpaceCache.getInstance();
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

        if (room == null || channel == null) {
            return false;
        }
        if (!nameSpaceCache.hasKey(channel)) {
            createNameSpace(channel);
        }
        // 初始化
        return true;
    }

    /**
     * 创建Namespace
     *
     * @param channel
     */
    public void createNameSpace(String channel) {
        // 创建SocketIONamespace
        Optional<SocketIONamespace> namespaceOptional = nameSpaceHandler.create(channel);
        namespaceOptional.ifPresent(namespace -> {
            // 放入缓存
            nameSpaceCache.set(channel, namespace);
        });

    }
}
