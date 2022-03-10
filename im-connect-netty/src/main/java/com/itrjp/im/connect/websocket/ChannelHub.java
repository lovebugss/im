package com.itrjp.im.connect.websocket;

import com.itrjp.im.connect.config.WebSocketProperties;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * NamespacesHub
 *
 * @author renjp
 * @date 2022/3/10 00:40
 */
public class ChannelHub {
    /**
     * 所有channels
     */
    private final Map<String, Channel> channels = new ConcurrentHashMap<>();
    WebSocketProperties properties;

    public ChannelHub(WebSocketProperties configure) {
        properties = configure;
    }


    /**
     * 创建频道
     *
     * @param channelId
     * @return
     */
    public Channel create(String channelId) {

        return channels.computeIfAbsent(channelId, (k) -> new Channel(channelId));
    }

    public void remove(String channelId) {
        Channel remove = channels.remove(channelId);
        if (remove != null) {
            // TODO 触发断开事件

        }
    }

    /**
     * 获取频道
     *
     * @param channelId
     * @return
     */
    public Channel getChannel(String channelId) {
        return channels.get(channelId);
    }

    public Collection<Channel> getAllChannels() {
        return channels.values();
    }
}
