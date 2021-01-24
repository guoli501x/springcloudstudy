package cn.guoli.springcloud.controller;

import cn.guoli.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 功能描述
 *
 * @author guoli
 * @Date 2020/4/18 2:37
 */
@RestController
@Slf4j
@RequestMapping(value = "/payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/ok/{id}")
    public String ok(@PathVariable("id") int id) {
        return "端口：" + serverPort + "; " + paymentService.ok(id);
    }

    @GetMapping("/timeout/{id}")
    public String timeout(@PathVariable("id") int id) {
        return "端口：" + serverPort + "; " + paymentService.timeout(id);
    }

    @GetMapping("/circuitBreaker/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") int id) {
        return paymentService.paymentCircuitBreaker(id);
    }
}
