package cn.guoli.springcloud.service;

import cn.guoli.springcloud.entities.CommonResult;
import cn.guoli.springcloud.entities.Payment;
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
@FeignClient(value = "PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping(value = "/payment/getById/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") long id);

    // 测试feign的超时控制
    @GetMapping("/payment/feign/timeout")
    String testFeignTimeout();
}
