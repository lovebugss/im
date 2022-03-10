package com.itrjp.im.gateway.filter;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.config.LoadBalancerProperties;
import org.springframework.cloud.gateway.filter.LoadBalancerClientFilter;
import org.springframework.web.server.ServerWebExchange;

import java.util.Map;

//@Component
public class IMLoadBalancerClientFilter extends LoadBalancerClientFilter {

    public IMLoadBalancerClientFilter(LoadBalancerClient loadBalancer, LoadBalancerProperties properties) {
        super(loadBalancer, properties);
    }


    /**
     * 实现转发到socketio集群，但是集群内socketio服务端口必须固定
     */
    protected ServiceInstance choose(ServerWebExchange exchange) {
        System.out.println("IMLoadBalancerClientFilter#choose");
        ServiceInstance serviceInstance = super.choose(exchange);
        if (null != serviceInstance && "im-connect".equals(serviceInstance.getServiceId())
                && exchange.getRequest().getPath().toString().contains("/socket.io/")) {
            Map<String, String> metadata = serviceInstance.getMetadata();
            String socketioPort = metadata.get("socketio-port");
            int sport;
            if (socketioPort != null && !"".equals(socketioPort)) {
                try {
                    sport = Integer.parseInt(socketioPort);
                } catch (NumberFormatException e) {
                    sport = 18073;
                }
            } else {
                sport = 18073;
            }
            // Specify port 23456
            return new DefaultServiceInstance(serviceInstance.getInstanceId(), serviceInstance.getServiceId(),
                    serviceInstance.getHost(), sport, serviceInstance.isSecure());
        }
        return serviceInstance;
    }
}
