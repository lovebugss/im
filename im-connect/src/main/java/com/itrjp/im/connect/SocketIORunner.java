package com.itrjp.im.connect;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SocketIORunner implements CommandLineRunner, DisposableBean {
    private final SocketIOServer server;

    public SocketIORunner(SocketIOServer server) {
        this.server = server;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("server run...");
        server.start();
    }

    @Override
    public void destroy() throws Exception {
        log.info("server stop...");
        server.stop();
    }
}
