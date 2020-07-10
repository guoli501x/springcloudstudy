package cn.guoli.springcloud.controller;

import cn.guoli.springcloud.entities.CommonResult;
import cn.guoli.springcloud.entities.Payment;
import cn.guoli.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 功能描述
 *
 * @author guoli
 * @Date 2020/6/27 17:52
 */
@RestController
@Slf4j
@RequestMapping(value = "/order/feign")
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/getById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") long id) {
        return paymentFeignService.getPaymentById(id);
    }

    // 测试feign的超时控制,openfeign默认1s
    @GetMapping("/feign/timeout")
    public String testFeignTimeout() {
        return paymentFeignService.testFeignTimeout();
    }
}
