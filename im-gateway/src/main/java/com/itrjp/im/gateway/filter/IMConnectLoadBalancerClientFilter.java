package com.itrjp.im.gateway.filter;

import org.springframework.cloud.gateway.config.LoadBalancerProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.ReactiveLoadBalancerClientFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//@Component
public class IMConnectLoadBalancerClientFilter extends ReactiveLoadBalancerClientFilter {

    public IMConnectLoadBalancerClientFilter(org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory clientFactory, LoadBalancerProperties properties) {
        super(clientFactory, properties);
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return super.filter(exchange, chain);
    }
}
