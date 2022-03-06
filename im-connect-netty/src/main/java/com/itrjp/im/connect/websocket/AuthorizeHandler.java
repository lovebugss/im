package com.itrjp.im.connect.websocket;

import com.itrjp.im.connect.service.ChatService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

/**
 * 鉴权处理器
 *
 * @author renjp
 * @date 2022/3/5 23:23
 */
public class AuthorizeHandler extends ChannelInboundHandlerAdapter {
    private final String connectUrl;
    private final ChatService chatService;

    public AuthorizeHandler(String connectUrl, ChatService chatService) {
        this.connectUrl = connectUrl;
        this.chatService = chatService;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            Channel channel = ctx.channel();
            QueryStringDecoder queryString = new QueryStringDecoder(request.uri());
            Map<String, List<String>> parameters = queryString.parameters();
            if (queryString.uri().equals(connectUrl) && parameters != null) {
                if (authorize(channel, request, parameters)) {
                    ctx.fireChannelRead(msg);
                }
            }
        }
    }

    /**
     * @param channel
     * @param request
     * @param parameters
     * @return
     */
    private boolean authorize(Channel channel, FullHttpRequest request, Map<String, List<String>> parameters) {

        // 参数检查
        return chatService.authorize();
    }
}
