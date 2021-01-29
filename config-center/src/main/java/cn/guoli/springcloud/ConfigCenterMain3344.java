/*
 * Copyright (c) 2020-2020 guoli.Co.Ltd. All rights reserved.
 */

package cn.guoli.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 功能描述
 *
 * @Author guoli
 * @Since 2021-01-28 21:47
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigCenterMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class, args);
    }
}
