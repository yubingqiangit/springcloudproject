package com.yu.config;/**
 *
 * @author yubingqian
 * @date 2020-12-16 9:38
 *
 */

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 根据url进行限流
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/16 9:38 
 */
public class UriKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getURI().getPath());
    }

}
