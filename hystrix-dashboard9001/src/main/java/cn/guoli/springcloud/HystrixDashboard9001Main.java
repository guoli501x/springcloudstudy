/*
 * Copyright (c) 2020-2020 guoli.Co.Ltd. All rights reserved.
 */

package cn.guoli.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * hystrix图形监控界面
 *
 * @Author guoli
 * @Since 2021-01-25 20:28
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboard9001Main {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboard9001Main.class, args);
    }
}
