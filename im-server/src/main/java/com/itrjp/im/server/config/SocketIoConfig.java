package com.itrjp.im.server.config;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.corundumstudio.socketio.store.StoreFactory;
import com.itrjp.im.server.enums.EventEnum;
import com.itrjp.im.server.listener.AuthListener;
import com.itrjp.im.server.listener.IMConnectListener;
import com.itrjp.im.server.listener.MessageListener;
import com.itrjp.im.server.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketIoConfig {
    @Value("${server.host:127.0.0.1}")
    private String hostname;

    @Bean
    SocketIOServer socketIOServer(MessageListener messageListener, StoreFactory storeFactory) {
        com.corundumstudio.socketio.Configuration configuration = new com.corundumstudio.socketio.Configuration();
        configuration.setHostname(hostname);
        configuration.setPort(9888);
        // 设置store
        configuration.setStoreFactory(storeFactory);
        // 设置鉴权监听器
        configuration.setAuthorizationListener(new AuthListener());
        SocketIOServer socketIOServer = new SocketIOServer(configuration);
        socketIOServer.addConnectListener(new IMConnectListener());
        socketIOServer.addEventListener(EventEnum.MESSAGE.getCode(), Message.class, messageListener);
        return socketIOServer;
    }

    @Bean
    SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketIOServer) {
        return new SpringAnnotationScanner(socketIOServer);
    }

    @Bean
    MessageListener messageListener() {
        return new MessageListener();
    }


}
