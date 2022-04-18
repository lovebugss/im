package com.itrjp.im.status;

import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/19 19:31
 */
//@Service
public class StatusRunner {
    private final NamingService namingService;

    public StatusRunner(NamingService namingService) {
        this.namingService = namingService;
    }
}
