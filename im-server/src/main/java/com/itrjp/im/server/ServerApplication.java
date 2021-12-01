package com.itrjp.im.server;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 程序主入口
 * 1. 初始化配置
 * 2. 启动server
 *
 * @author renjp
 */
@SpringBootApplication
public class ServerApplication implements CommandLineRunner {
    private final SocketIOServer server;

    public ServerApplication(SocketIOServer server) {
        this.server = server;
    }

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        server.start();
    }
}
