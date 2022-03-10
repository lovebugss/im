package com.itrjp.im.connect.websocket;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * HandshakeData
 *
 * @author renjp
 * @date 2022/3/10 23:01
 */
public class HandshakeData implements Serializable {
    private final LocalDateTime time = LocalDateTime.now();
    private Map<String, List<String>> urlParams;
    private String url;

    public HandshakeData() {
    }

    public HandshakeData(Map<String, List<String>> urlParams, String url) {
        this.urlParams = urlParams;
        this.url = url;
    }

    public Map<String, List<String>> getUrlParams() {
        return urlParams;
    }

    public void setUrlParams(Map<String, List<String>> urlParams) {
        this.urlParams = urlParams;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
