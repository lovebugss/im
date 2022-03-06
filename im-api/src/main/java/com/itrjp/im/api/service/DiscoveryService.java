package com.itrjp.im.api.service;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;

/**
 * 服务发现 service
 *
 * @author renjp
 * @date 2022/3/6 21:49
 */
public interface DiscoveryService {

    List<Instance> getAllInstances(String serviceName);
}
