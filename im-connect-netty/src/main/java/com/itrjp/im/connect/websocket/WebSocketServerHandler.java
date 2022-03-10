package com.itrjp.im.connect.websocket;

import com.google.protobuf.MessageLiteOrBuilder;
import com.itrjp.im.common.protobuf.MessageProtobuf;
import com.itrjp.im.connect.config.WebSocketProperties;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static io.netty.handler.codec.http.HttpMethod.GET;
import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpResponseStatus.FORBIDDEN;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/6 11:35
 */
@Slf4j
@ChannelHandler.Sharable
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
    private static final String WEBSOCKET_PATH = "/ws";
    private final ChannelHub channelHub;
    private WebSocketServerHandshaker handshaker;
    private WebSocketProperties webSocketProperties;
    private MessageHandler messageListener;

    public WebSocketServerHandler(WebSocketProperties webSocketProperties, MessageHandler messageListener, ChannelHub channelHub) {
        this.webSocketProperties = webSocketProperties;
        this.messageListener = messageListener;
        this.channelHub = channelHub;
    }

    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
        // Generate an error page if response getStatus code is not OK (200).
        HttpResponseStatus responseStatus = res.status();
        if (responseStatus.code() != 200) {
            ByteBufUtil.writeUtf8(res.content(), responseStatus.toString());
            HttpUtil.setContentLength(res, res.content().readableBytes());
        }
        // Send the response and close the connection if necessary.
        boolean keepAlive = HttpUtil.isKeepAlive(req) && responseStatus.code() == 200;
        HttpUtil.setKeepAlive(res, keepAlive);
        ChannelFuture future = ctx.write(res); // Flushed in channelReadComplete()
        if (!keepAlive) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

    private String getWebSocketLocation(FullHttpRequest req) {
        String location = req.headers().get(HttpHeaderNames.HOST) + WEBSOCKET_PATH;
        if (webSocketProperties.isUseSSL()) {
            return "wss://" + location;
        } else {
            return "ws://" + location;
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        }
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        // Handle a bad request.
        if (!req.decoderResult().isSuccess()) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(req.protocolVersion(), BAD_REQUEST,
                    ctx.alloc().buffer(0)));
            return;
        }

        // 非Get 请求
        if (!GET.equals(req.method())) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(req.protocolVersion(), FORBIDDEN,
                    ctx.alloc().buffer(0)));
            return;
        }
        // 鉴权

        // Handshake
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                getWebSocketLocation(req), null, true, 5 * 1024 * 1024);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            ChannelPipeline pipeline = ctx.pipeline();
            pipeline.remove(ctx.name());

            pipeline.addLast(new ChunkedWriteHandler());

            pipeline.addLast(new WebSocketServerCompressionHandler());
            pipeline.addLast(new WebSocketFrameAggregator(Integer.MAX_VALUE));
//            pipeline.addLast(new WebSocketServerProtocolHandler(WEBSOCKET_PATH, null, true));

            pipeline.addLast(new ProtobufVarint32FrameDecoder());
            pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());

            //将WebSocketFrame转为ByteBuf 以便后面的 ProtobufDecoder 解码
            pipeline.addLast(new WebSocketFrameToProtobufDecoder());

            pipeline.addLast(new ProtobufDecoder(MessageProtobuf.Message.getDefaultInstance()));
            //自定义入站处理
            pipeline.addLast(messageListener);

            //出站处理 将protoBuf实例转为WebSocketFrame
            pipeline.addLast(new ProtobufEncoder() {
                @Override
                protected void encode(ChannelHandlerContext ctx, MessageLiteOrBuilder msg, List<Object> out) throws Exception {
                    MessageProtobuf.Message mpMsg = (MessageProtobuf.Message) msg;
                    WebSocketFrame frame = new BinaryWebSocketFrame(Unpooled.wrappedBuffer(mpMsg.toByteArray()));
                    out.add(frame);
                }
            });
            handshaker.handshake(ctx.channel(), req).addListener(future -> {
                if (future.isSuccess()) {
                    // 握手成功, 连接客户端

                    log.info("handshaker success");
                } else {
                    handshaker.close(ctx.channel(), new CloseWebSocketFrame());
                }
            });
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
