/*
 * Copyright (c) 2020-2020 guoli.Co.Ltd. All rights reserved.
 */

package cn.guoli.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * gateway配置类
 *
 * @Author guoli
 * @Since 2021-01-26 21:43
 */
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator getRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("route", r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }
}
