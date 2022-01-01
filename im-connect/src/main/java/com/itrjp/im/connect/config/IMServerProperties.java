package com.itrjp.im.connect.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "im.server")
public class IMServerProperties {
    /**
     * host
     */
    @Value("${im.server.host:${server.host:}}")
    private String host;
    /**
     * port
     */
    @Value("${im.server.port:${server.port:}}")
    private int port;
    private boolean reuseAddress = false;

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

    public boolean isReuseAddress() {
        return reuseAddress;
    }

    public void setReuseAddress(boolean reuseAddress) {
        this.reuseAddress = reuseAddress;
    }
}
