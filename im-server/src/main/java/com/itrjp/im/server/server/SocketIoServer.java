package com.itrjp.im.server.server;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.store.RedissonStoreFactory;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author ren
 */
public class SocketIoServer {

    Configuration configuration;

    public SocketIoServer() {
    }

    public static void run(String[] args) {
        SocketIoServer socketIOServer = new SocketIoServer();
        socketIOServer.run();
    }

    private void run() {
        configuration = new Configuration();
        Config redissonConfig = new Config();
        redissonConfig.useSingleServer()
                .setAddress("127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(redissonConfig);

        RedissonStoreFactory redissonStoreFactory = new RedissonStoreFactory(redisson);
        configuration.setStoreFactory(redissonStoreFactory);
        SocketIOServer socketIOServer = new SocketIOServer(configuration);
        socketIOServer.start();
    }
}
