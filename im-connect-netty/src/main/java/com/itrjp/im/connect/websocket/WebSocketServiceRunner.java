package com.itrjp.im.connect.websocket;

import com.itrjp.im.connect.config.WebSocketProperties;
import com.itrjp.im.connect.service.ChatService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * WebSocket server启动类
 *
 * @author renjp
 * @date 2022/3/5 15:34
 */
@Component
public class WebSocketServiceRunner implements CommandLineRunner, DisposableBean {

    private final WebSocketProperties configuration;
    private final ChatService chatService;
    private WebSocketServer server;


    public WebSocketServiceRunner(WebSocketProperties configuration, ChatService chatService) {
        this.configuration = configuration;
        this.chatService = chatService;
    }

    @Override
    public void destroy() throws Exception {
        if (server != null) {
            server.close();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        server = new WebSocketServer(configuration, chatService);
        server.start();
    }
}