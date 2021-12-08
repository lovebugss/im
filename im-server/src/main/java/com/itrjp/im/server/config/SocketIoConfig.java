package com.itrjp.im.server.config;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.corundumstudio.socketio.store.StoreFactory;
import com.itrjp.im.server.listener.IMAuthorizationListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Socket-IO 相关配置
 *
 * @author renjp
 */
@Configuration
public class SocketIoConfig {
    @Value("${server.host:localhost}")
    private String hostname;

    @Bean
    SocketIOServer socketIOServer(StoreFactory storeFactory, SocketConfig socketConfig) {
        com.corundumstudio.socketio.Configuration configuration = new com.corundumstudio.socketio.Configuration();
        configuration.setHostname(hostname);
        configuration.setPort(9888);
        // 设置store
        configuration.setStoreFactory(storeFactory);
        // 设置鉴权监听器
        configuration.setAuthorizationListener(authorizationListener());
        configuration.setSocketConfig(socketConfig);
        SocketIOServer socketIOServer = new SocketIOServer(configuration);
        socketIOServer.addNamespace("ch_001");
        return socketIOServer;
    }

    @Bean
    SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketIOServer) {
        return new SpringAnnotationScanner(socketIOServer);
    }


    @Bean
    SocketConfig socketConfig() {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setReuseAddress(true);
        return socketConfig;
    }


    /**
     * 鉴权
     *
     * @return AuthorizationListener
     */
    @Bean
    AuthorizationListener authorizationListener() {
        return new IMAuthorizationListener();
    }
}
