package com.itrjp.im.filter;

import com.itrjp.im.filter.config.FilterConfig;
import com.itrjp.im.filter.server.FilterServer;

/**
 * 消息过滤服务
 * 程序主入口
 *
 * @author renjp
 */
public class FilterMain {
    public static void main(String[] args) {

        // 加载配置文件
        FilterConfig filterConfig = new FilterConfig();

        FilterServer filterServer = new FilterServer(filterConfig);
        // 服务启动
        filterServer.start();

    }
}
