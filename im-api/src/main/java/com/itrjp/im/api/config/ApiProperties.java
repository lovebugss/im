package com.itrjp.im.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/6 21:45
 */
@Data
@ConfigurationProperties("im.api")
public class ApiProperties {

    private String secretKey;
}
