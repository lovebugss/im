package com.itrjp.im.gateway.filter;

import com.itrjp.core.util.JsonUtils;
import com.itrjp.im.common.exception.UnAuthorizedException;
import com.itrjp.im.common.result.Result;
import com.itrjp.im.gateway.service.ConnectAuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.itrjp.im.common.conts.URLConstant.SOCKET_IO_PREFIX;

/**
 * TODO 连接鉴权过滤器
 *
 * @author renjo
 * @date 2022-01-16 09:34:00
 */
@Slf4j
@Configuration
public class ConnectAuthorizationFilter implements GlobalFilter {
    private final ConnectAuthorizationService connectAuthorizationService;

    public ConnectAuthorizationFilter(ConnectAuthorizationService connectAuthorizationService) {
        this.connectAuthorizationService = connectAuthorizationService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (!needAuthorization(request)) {
            return chain.filter(exchange);
        }
        // 首先判断参数是否合法
        if (!connectAuthorizationService.auth(request.getQueryParams())) {
            return failed(exchange);
        }
        // 判断当前房间是否可以加入

        return chain.filter(exchange);
    }

    /**
     * 失败
     *
     * @param exchange
     * @return
     */
    private Mono<Void> failed(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer wrap = response.bufferFactory()
                .wrap(JsonUtils.serializeToBytes(Result.error(new UnAuthorizedException())));
        return response.writeWith(Mono.just(wrap));
    }

    private boolean needAuthorization(ServerHttpRequest request) {
        return request.getURI().getPath().contains(SOCKET_IO_PREFIX);
    }
}
