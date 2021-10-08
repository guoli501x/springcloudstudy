/*
 * Copyright (c) 2020-2020 guoli.Co.Ltd. All rights reserved.
 */

package cn.guoli.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 路由日志过滤器
 * order注解：加载过滤器的优先级，值越小优先级越高
 *
 * @Author guoli
 * @Since 2021-01-27 22:51
 */
@Component
@Slf4j
@Order(0)
public class LogGatewayFilter implements GlobalFilter{
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 校验参数username不能为空
//        String username = exchange.getRequest().getQueryParams().getFirst("username");
//        if (StringUtils.isEmpty(username)) {
//            log.warn("缺少参数username");
//            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
//            return exchange.getResponse().setComplete();
//        }
        return chain.filter(exchange);
    }
}
