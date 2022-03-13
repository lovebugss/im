package com.itrjp.im.connect.websocket;

import com.itrjp.im.connect.config.WebSocketProperties;
import com.itrjp.im.connect.service.ChatService;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;

/**
 * WebSocketInitializer
 *
 * @author renjp
 * @date 2022/2/19 18:02
 */
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslCtx;
    private final WebSocketServerHandler webSocketServerHandler;

    public WebSocketChannelInitializer(WebSocketProperties webSocketProperties, SslContext sslCtx, ChatService chatService, ChatRoomHub chatRoomHub) {
        this.sslCtx = sslCtx;
        webSocketServerHandler = new WebSocketServerHandler(webSocketProperties, chatRoomHub, chatService);
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc()));
        }
        pipeline.addLast("http-server-codec", new HttpServerCodec());
        pipeline.addLast("http-object-aggregator", new HttpObjectAggregator(65536));
        pipeline.addLast("websocket-server-handler", webSocketServerHandler);

    }
}
