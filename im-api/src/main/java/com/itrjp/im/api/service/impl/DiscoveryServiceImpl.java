package com.itrjp.im.api.service.impl;

import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.itrjp.im.api.service.DiscoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/6 21:50
 */
@Service
public class DiscoveryServiceImpl implements DiscoveryService {

    @Autowired
    NamingService namingService;

    @Override
    public List<Instance> getAllInstances(String serviceName) {
        return null;
    }
}
