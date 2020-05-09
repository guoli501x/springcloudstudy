package cn.guoli.springcloud.controller;

import cn.guoli.springcloud.entities.CommonResult;
import cn.guoli.springcloud.entities.Payment;
import cn.guoli.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class PaymentControler {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/add")
    public CommonResult<Payment> addPayment(@RequestBody Payment payment) {
        log.info("start add");
        int result = paymentService.addPayment(payment);
        if (result > 0) {
            return new CommonResult(200, "success, serverPort: " + serverPort, result);
        } else {
            log.error("add payment field");
            return new CommonResult(500, "add payment field, serverPort: " + serverPort, result);
        }
    }

    @GetMapping(value = "/getById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            log.info(id + "is null");
            return new CommonResult(500, "payment is null, serverPort: " + serverPort);
        } else {
            log.info("get " + id + " success");
            return new CommonResult(200, "success, serverPort: " + serverPort, payment);
        }
    }
}
