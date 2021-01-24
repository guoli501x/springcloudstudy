package cn.guoli.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 功能描述
 *
 * @author guoli
 * @Date 2020/7/11 0:20
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class PaymentHystrixMain {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain.class, args);
    }
}
