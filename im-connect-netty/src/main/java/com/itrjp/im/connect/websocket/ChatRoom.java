package com.itrjp.im.connect.websocket;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Channel
 *
 * @author renjp
 * @date 2022/3/10 00:38
 */
public class ChatRoom {
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private String name;

    public ChatRoom(String name) {
        this.name = name;
    }

    public void join(Channel channel) {
        channels.add(channel);
    }

    public void sendMessage() {

    }

}
