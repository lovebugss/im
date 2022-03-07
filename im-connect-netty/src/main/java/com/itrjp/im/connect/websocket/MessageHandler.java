package com.itrjp.im.connect.websocket;

import com.itrjp.im.common.protobuf.MessageProtobuf;
import com.itrjp.im.connect.listener.MessageListener;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/5 17:54
 */
@ChannelHandler.Sharable
public class MessageHandler extends SimpleChannelInboundHandler<MessageProtobuf.Message> {
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private final MessageListener messageListener;

    public MessageHandler(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        super.handlerAdded(ctx);
        channels.add(channel);
    }

    //每当从服务端收到客户端断开时，客户端的 Channel 移除 ChannelGroup 列表中，并通知列表中的其他客户端 Channel
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel currChannel = ctx.channel();
        channels.writeAndFlush("[服务器] - " + currChannel.remoteAddress() + " 离开\n");

    }


    //每当从服务端读到客户端写入信息时，将信息转发给其他客户端的 Channel。
    // 其中如果你使用的是 Netty 5.x 版本时，需要把 channelRead0() 重命名为messageReceived()
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String msg) {
        Channel incoming = channelHandlerContext.channel();
        for (Channel channel : channels) {//遍历ChannelGroup中的channel
            if (channel != incoming) {//找到加入到ChannelGroup中的channel后，将录入的信息回写给除去发送信息的客户端
                channel.writeAndFlush("[" + incoming.remoteAddress() + "]" + msg + "\n");
            } else {
                channel.writeAndFlush("[服务器消息]" + msg + "\n");
            }
        }
    }

    //服务端监听到客户端活动
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        Channel incoming = ctx.channel();
        System.out.println("服务器:" + incoming.remoteAddress() + "上线");
    }

    //服务端监听到客户端不活动
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
        Channel incoming = ctx.channel();
        System.out.println("服务器:" + incoming.remoteAddress() + "掉线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Channel incoming = ctx.channel();
        System.out.println("服务器:" + incoming.remoteAddress() + "异常");
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtobuf.Message msg) throws Exception {
        System.out.println("111：" + msg.toString());

        // 消息
        messageListener.onMessage(msg);
        //响应消息
        MessageProtobuf.Message.Builder builder = MessageProtobuf.Message.newBuilder();
        builder.setType(MessageProtobuf.MessageType.TEXT);
        builder.setContent("好的客户端 i received ");
        MessageProtobuf.Message build = builder.build();
        ctx.channel().writeAndFlush(new MessageProtobuf.Message[]{build, build});

    }
}
