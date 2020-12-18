package com.yu.config;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 根据IP进行限流
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/16 9:34 
 */

public class HostAddKeyResolver implements KeyResolver {

    public static final String BEAN_NAME = "hostAddKeyResolver";

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());

    }
}
