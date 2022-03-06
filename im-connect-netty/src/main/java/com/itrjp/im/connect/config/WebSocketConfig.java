package com.itrjp.im.connect.config;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/6 20:40
 */
@Configuration
public class WebSocketConfig {

    @Bean
    ChannelGroup allChannel() {
        return new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    }
}
