package com.itrjp.im.connect.websocket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.websocketx.*;

import java.util.List;

/**
 * WebSocketFrame 转 protobuf实体
 *
 * @author renjp
 * @date 2022/3/10 22:58
 */
public class WebSocketFrameToProtobufDecoder extends MessageToMessageDecoder<WebSocketFrame> {
    @Override
    protected void decode(ChannelHandlerContext ctx, WebSocketFrame msg, List<Object> out) throws Exception {
        ByteBuf byteBuf = msg.content();
        if (msg instanceof BinaryWebSocketFrame) {
            // 二进制消息
            System.out.println("BinaryWebSocketFrame Received : ");
            byteBuf.retain();
            out.add(msg.content());
        } else if (msg instanceof TextWebSocketFrame) {
            // 文本消息
            System.out.println("TextWebSocketFrame Received : ");
            System.out.println(((TextWebSocketFrame) msg).text());
        } else if (msg instanceof PingWebSocketFrame) {
            // ping
            System.out.println("PingWebSocketFrame Received : ");
            System.out.println(((PingWebSocketFrame) msg).content());
        } else if (msg instanceof PongWebSocketFrame) {
            // pong
            System.out.println("PongWebSocketFrame Received : ");
            System.out.println(((PongWebSocketFrame) msg).content());
        } else if (msg instanceof CloseWebSocketFrame) {
            // close
            System.out.println("CloseWebSocketFrame Received : ");
            System.out.println("ReasonText :" + ((CloseWebSocketFrame) msg).reasonText());
            System.out.println("StatusCode : " + ((CloseWebSocketFrame) msg).statusCode());
            ctx.channel().close();
        } else if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            QueryStringDecoder queryString = new QueryStringDecoder(request.uri());

        } else {
            System.out.println("Unsupported WebSocketFrame");
        }
    }
}
