package com.itrjp.im.server;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 程序主入口
 * 1. 初始化配置
 * 2. 启动server
 *
 * @author renjp
 */
@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan
public class ServerApplication implements CommandLineRunner, DisposableBean {
    private final SocketIOServer server;

    public ServerApplication(SocketIOServer server) {
        this.server = server;
    }

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("server run...");
        server.start();
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("server stop...");
        server.stop();
    }
}
