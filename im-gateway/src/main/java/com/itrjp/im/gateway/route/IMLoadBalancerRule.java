package com.itrjp.im.gateway.route;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.cloud.nacos.ribbon.ExtendBalancer;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.common.utils.StringUtils;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
import com.netflix.loadbalancer.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


//@Component
public class IMLoadBalancerRule extends AbstractLoadBalancerRule {
    private static final Logger LOGGER = LoggerFactory.getLogger(IMLoadBalancerRule.class);
    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;
    @Autowired
    private NacosServiceManager nacosServiceManager;


    @Override
    public Server choose(Object key) {
        LOGGER.info("IMLoadBalancerRule#choose");
        try {
            // 获取集群名称
            String clusterName = this.nacosDiscoveryProperties.getClusterName();
            // 组
            String group = this.nacosDiscoveryProperties.getGroup();
            DynamicServerListLoadBalancer<?> loadBalancer = (DynamicServerListLoadBalancer) this.getLoadBalancer();
            // 名称, 什么名称?
            String name = loadBalancer.getName();
            NamingService namingService = this.nacosServiceManager.getNamingService(this.nacosDiscoveryProperties.getNacosProperties());
            List<Instance> instances = namingService.selectInstances(name, group, true);
            if (CollectionUtils.isEmpty(instances)) {
                LOGGER.warn("no instance in service {}", name);
                return null;
            } else {
                List<Instance> instancesToChoose = instances;
                if (StringUtils.isNotBlank(clusterName)) {
                    List<Instance> sameClusterInstances = (List) instances.stream().filter((instancex) -> {
                        return Objects.equals(clusterName, instancex.getClusterName());
                    }).collect(Collectors.toList());
                    if (!CollectionUtils.isEmpty(sameClusterInstances)) {
                        instancesToChoose = sameClusterInstances;
                    } else {
                        LOGGER.warn("A cross-cluster call occurs，name = {}, clusterName = {}, instance = {}", new Object[]{name, clusterName, instances});
                    }
                }

                Instance instance = ExtendBalancer.getHostByRandomWeight2(instancesToChoose);
                return new NacosServer(instance);
            }

        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}
