/*
 * Copyright (c) 2020-2020 guoli.Co.Ltd. All rights reserved.
 */

package cn.guoli.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * gateway服务网关
 *
 * @Author guoli
 * @Since 2021-01-26 20:15
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayMain9527 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMain9527.class, args);
    }
}
