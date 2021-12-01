package com.itrjp.im.server.config;

import com.corundumstudio.socketio.store.RedissonStoreFactory;
import com.corundumstudio.socketio.store.StoreFactory;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RedissonConfig 配置类
 */
@Configuration
public class RedissonConfig {

    @Value("${redisson.address:127.0.0.1:6379}")
    private String address;

    @Bean(destroyMethod = "shutdown")
    StoreFactory redissonStoreFactory(Config redissonConfig) {
        RedissonClient redisson = Redisson.create(redissonConfig);

        return new RedissonStoreFactory(redisson);
    }

    @Bean
    Config redissonConfig() {
        Config redissonConfig = new Config();
        redissonConfig.useSingleServer().setAddress(address);

        return redissonConfig;
    }
}
