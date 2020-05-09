package cn.guoli.springcloud.controller;

import cn.guoli.springcloud.entities.CommonResult;
import cn.guoli.springcloud.entities.Payment;
import com.netflix.discovery.DiscoveryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能描述
 *
 * @author guoli
 * @Date 2020/4/18 17:58
 */
@RestController
@Slf4j
@RequestMapping(value = "/order")
public class OrderController {
    private static final String PAYMENT_URL = "http://PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/add")
    public CommonResult<Payment> add(Payment payment) {
        log.info("add payment start");
        return restTemplate.postForObject(PAYMENT_URL + "/payment/add", payment, CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/getById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") long id) {
        log.info("getById payment start");
        return restTemplate.getForObject(PAYMENT_URL + "/payment/getById/" + id, CommonResult.class);
    }
}
