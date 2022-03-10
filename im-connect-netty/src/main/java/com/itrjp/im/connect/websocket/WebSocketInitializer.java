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
 * TODO
 *
 * @author renjp
 * @date 2022/2/19 18:02
 */
public class WebSocketInitializer extends ChannelInitializer<SocketChannel> {

    private final WebSocketProperties webSocketProperties;
    private final ChannelHub channelHub;
    private final SslContext sslCtx;

    private WebSocketServerHandler webSocketServerHandler;

    public WebSocketInitializer(WebSocketProperties webSocketProperties, SslContext sslCtx, ChatService chatService, ChannelHub channelHub) {
        this.webSocketProperties = webSocketProperties;
        this.sslCtx = sslCtx;
        this.channelHub = channelHub;
        webSocketServerHandler = new WebSocketServerHandler(webSocketProperties, new MessageHandler(chatService), channelHub);
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc()));
        }
        pipeline.addLast("http-server-codec", new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(65536));
        pipeline.addLast("websocket-server-handler", webSocketServerHandler);

    }
}
