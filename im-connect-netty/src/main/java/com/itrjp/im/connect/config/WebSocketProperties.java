package com.itrjp.im.connect.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置类
 *
 * @author renjp
 * @date 2022/3/5 15:40
 */
@ConfigurationProperties("im.connect")
public class WebSocketProperties {
    private int bossThreads;
    private int workThreads;
    /**
     * host
     */
    @Value("${im.server.host:${server.address}}")
    private String host;
    /**
     * port
     */
    @Value("${im.server.port:${server.port:18001}}")
    private int port;

    private Tcp tcp = new Tcp();
    private boolean useSSL;

    public boolean isUseSSL() {
        return useSSL;
    }

    public void setUseSSL(boolean useSSL) {
        this.useSSL = useSSL;
    }

    public int getBossThreads() {
        return bossThreads;
    }

    public void setBossThreads(int bossThreads) {
        this.bossThreads = bossThreads;
    }

    public int getWorkThreads() {
        return workThreads;
    }

    public void setWorkThreads(int workThreads) {
        this.workThreads = workThreads;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Tcp getTcp() {
        return tcp;
    }

    public void setTcp(Tcp tcp) {
        this.tcp = tcp;
    }

    public static class Tcp {
        private int backlog = 1024;

        public int getBacklog() {
            return backlog;
        }

        public void setBacklog(int backlog) {
            this.backlog = backlog;
        }
    }
}
