package cn.guoli.springcloud.service;

import cn.guoli.springcloud.service.impl.HystrixPaymentFeignServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 功能描述
 *
 * @author guoli
 * @Date 2020/6/27 17:41
 */
@Component
@FeignClient(value = "HYSTRIX-PAYMENT-SERVICE", fallback = HystrixPaymentFeignServiceImpl.class)
public interface HystrixPaymentFeignService {
    /**
     * 测试feign的超时控制
     * @return 提示
     */
    @GetMapping("/payment/timeout/{id}")
    String timeout(@PathVariable("id") int id);
}
