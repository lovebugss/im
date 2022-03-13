package com.itrjp.im.connect.websocket;

import com.itrjp.im.connect.config.WebSocketProperties;
import io.netty.channel.Channel;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * NamespacesHub
 *
 * @author renjp
 * @date 2022/3/10 00:40
 */
public class ChatRoomHub {
    /**
     * 所有channels
     */
    private final Map<String, ChatRoom> chatRooms = new ConcurrentHashMap<>();
    WebSocketProperties properties;

    public ChatRoomHub(WebSocketProperties configure) {
        properties = configure;
    }


    /**
     * 创建频道
     *
     * @param roomId
     * @return
     */
    private ChatRoom create(String roomId) {

        return chatRooms.computeIfAbsent(roomId, (k) -> new ChatRoom(roomId));
    }

    public void createAndJoin(String roomId, Channel channel) {
        create(roomId).join(channel);
    }

    public void remove(String channelId) {
        ChatRoom remove = chatRooms.remove(channelId);
        if (remove != null) {
            // TODO 触发断开事件

        }
    }

    /**
     * 获取聊天室
     *
     * @param roomId
     * @return
     */
    public ChatRoom getChatRooms(String roomId) {
        return chatRooms.get(roomId);
    }

    public Collection<ChatRoom> getAllChannels() {
        return chatRooms.values();
    }
}
