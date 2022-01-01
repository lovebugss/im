package com.itrjp.im.connect.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.corundumstudio.socketio.store.StoreFactory;
import com.itrjp.im.connect.listener.IMAuthorizationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Socket-IO 相关配置
 *
 * @author renjp
 */
@Configuration
public class SocketIoConfig {
    private final IMServerProperties serverProperties;
    private final IMAuthorizationListener authorizationListener;

    public SocketIoConfig(IMServerProperties serverProperties, IMAuthorizationListener authorizationListener) {
        this.serverProperties = serverProperties;
        this.authorizationListener = authorizationListener;
    }


    @Bean
    SocketIOServer socketIOServer(StoreFactory storeFactory, SocketConfig socketConfig) {
        com.corundumstudio.socketio.Configuration configuration = new com.corundumstudio.socketio.Configuration();
        configuration.setHostname(serverProperties.getHost());
        configuration.setPort(serverProperties.getPort());
        configuration.setOrigin("http://localhost:8000");
        // 设置store
        configuration.setStoreFactory(storeFactory);
        // 设置鉴权监听器
        configuration.setAuthorizationListener(authorizationListener);
        configuration.setSocketConfig(socketConfig);
        return new SocketIOServer(configuration);
    }

    @Bean
    SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketIOServer) {
        return new SpringAnnotationScanner(socketIOServer);
    }


    @Bean
    SocketConfig socketConfig() {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setReuseAddress(serverProperties.isReuseAddress());
        return socketConfig;
    }

}
