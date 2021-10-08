package cn.guoli.springcloud.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * 网关限流配置类
 *
 * @author guoli
 * @data 2021-09-12 17:28
 */
@Configuration
public class RequestRateLimiterConfig {
    @Bean
    @Primary
    public KeyResolver apiKeyResolver() {
        // 按URL限流,即以每秒内请求数按URL分组统计，超出限流的url请求都将返回429状态
        return exchange -> Mono.just(exchange.getRequest().getPath().toString());
    }

    @Bean
    public KeyResolver userKeyResolver() {
        // 按用户限流
        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getQueryParams().getFirst("user")));
    }

    @Bean
    public KeyResolver ipKeyResolver() {
        // 按IP来限流
        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getHostName());
    }
}
