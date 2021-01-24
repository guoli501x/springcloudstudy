package cn.guoli.springcloud.service.impl;

import cn.guoli.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述
 *
 * @author guoli
 * @Date 2020/4/18 2:28
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String ok(int id) {
        return "线程是： " + Thread.currentThread().getName() + "; ok, id = " +id;
    }

    /**
     * 服务降级：
     * 设置 3 秒为正常处理时间，超过则执行兜底方法timeoutHandle
     * 异常也会执行兜底方法timeoutHandle
     * @param id id
     * @return return
     */
    @Override
    @HystrixCommand(fallbackMethod = "timeoutHandle", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String timeout(int id) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程是： " + Thread.currentThread().getName() + "; timeout, id = " +id;
    }

    public String timeoutHandle(int id) {
        return "线程是： " + Thread.currentThread().getName() + "; timeout, id = " +id + " 服务方兜底方法";
    }

    /**
     * 服务熔断
     * 第一个=====是否开启服务熔断-断路器
     * 第二个=====请求次数
     * 第三个=====时间范围，单位ms
     * 第四个=====失败率
     * @param id id
     * @return return
     */
    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10")
    })
    public String paymentCircuitBreaker(int id) {
        if (id < 0) {
            throw new RuntimeException("id 不能为负数- " + id);
        }
        return Thread.currentThread().getName() + " 调用成功， 流水号 " + UUID.randomUUID();
    }

    public String paymentCircuitBreakerFallback(int id) {
        return "服务熔断测试- id 不能为负数-";
    }

}
