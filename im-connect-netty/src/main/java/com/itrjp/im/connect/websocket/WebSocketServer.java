package com.itrjp.im.connect.websocket;


import com.itrjp.im.connect.config.WebSocketProperties;
import com.itrjp.im.connect.service.ChatService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.util.concurrent.Future;

/**
 * WebSocket Server
 *
 * @author renjp
 * @date 2022/2/18 16:54
 */
public class WebSocketServer {
    private SslContext sslCtx;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private WebSocketProperties configuration;
    private ServerBootstrap bootstrap;
    private ChatService chatService;
    private WebSocketInitializer webSocketInitializer;

    public WebSocketServer(WebSocketProperties configure, ChatService chatService) {
        this.configuration = configure;
        this.chatService = chatService;

    }

    public void init() throws Exception {
        // group
        bossGroup = new NioEventLoopGroup(configuration.getBossThreads());
        workerGroup = new NioEventLoopGroup(configuration.getWorkThreads());
        // ssl
        if (configuration.isUseSSL()) {
            sslCtx = createSslContext(configuration);
        }
        webSocketInitializer = new WebSocketInitializer(this.configuration, sslCtx, chatService);
    }

    /**
     * 创建ssl context
     *
     * @param configuration
     * @return
     * @throws Exception
     */
    private SslContext createSslContext(WebSocketProperties configuration) throws Exception {
        SelfSignedCertificate ssc = new SelfSignedCertificate();
        return SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
    }


    public void start() throws Exception {
        init();
        startSync().syncUninterruptibly();
    }

    private Future<Void> startSync() {

        bootstrap = new ServerBootstrap();
        bootstrap.option(ChannelOption.SO_BACKLOG, configuration.getTcp().getBacklog());
        Class<? extends ServerChannel> channelClass = NioServerSocketChannel.class;

        bootstrap.group(bossGroup, workerGroup)
                .channel(channelClass)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(webSocketInitializer);


        return bootstrap.bind(configuration.getPort())
                .addListener(future -> {
                    if (future.isSuccess()) {
                        System.out.println("WebSocket server started at port: " + configuration.getPort());
                    }
                });
    }

    public void close() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
