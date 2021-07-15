package cn.guoli.springcloud.controller;

import cn.guoli.springcloud.entities.CommonResult;
import cn.guoli.springcloud.entities.Payment;
import cn.guoli.springcloud.myloadbalancer.LoadBalancer;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
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

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalancer loadBalancer;

    @GetMapping(value = "/consumer/payment/add")
    public CommonResult<Payment> add(Payment payment) {
        log.info("add payment start");
        return restTemplate.postForObject(PAYMENT_URL + "/payment/add", payment, CommonResult.class);
    }

    @HystrixCommand(
            commandProperties = {
                    // 服务隔离设置，默认线程池隔离 THREAD，信号量 SEMAPHORE
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value = "THREAD"),
                    // 线程隔离中超时是否中断执行，默认true
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_INTERRUPT_ON_TIMEOUT, value = "true"),
                    // 同时有多少个线程可以调用兜底方法，默认10，超出就会报错，在服务隔离时设置使用
                    @HystrixProperty(name = HystrixPropertiesManager.FALLBACK_ISOLATION_SEMAPHORE_MAX_CONCURRENT_REQUESTS, value = "10")
            },
//            commandKey = "order-testThread",
            // 一组hystrix命令，默认类名
//            groupKey = "order-OrderController",
            // 线程池名称，默认取(hystrix-) + (groupKey) ，即默认同一个类中使用一个线程池
//            threadPoolKey = "order-pool-1",
            threadPoolProperties = {
                    // 核心线程数，默认10
                    @HystrixProperty(name = HystrixPropertiesManager.CORE_SIZE, value = "10"),
                    // 空闲线程最大存活时间，默认为 2，即两分钟
                    @HystrixProperty(name = HystrixPropertiesManager.KEEP_ALIVE_TIME_MINUTES, value = "2"),
                    // 队列的最大值，默认值为 -1
                    @HystrixProperty(name = HystrixPropertiesManager.MAX_QUEUE_SIZE, value = "10"),
                    // 执行拒绝策略的队列大小，默认5，所以当队列最大值大于5时，一般要设置此项
                    @HystrixProperty(name = HystrixPropertiesManager.QUEUE_SIZE_REJECTION_THRESHOLD, value = "10")
            },
            // 异常情况的兜底方法
            fallbackMethod = "fallbackMethod"
    )
    @GetMapping("/test/thread/{id}")
    public String testThread(@PathVariable("id") long id) throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        Thread.sleep(200);
        log.info("入参为：{}，线程池：{}", id, threadName);
        return "入参为：" + id + " 线程池：" + threadName;
    }

    @HystrixCommand(
            // 兜底方法
            fallbackMethod = "fallbackMethod",
            commandProperties = {
                    // 服务隔离设置，默认线程池隔离 THREAD，信号量 SEMAPHORE
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value = "SEMAPHORE"),
                    // 最大并发请求数，即信号量的大小，默认10
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_SEMAPHORE_MAX_CONCURRENT_REQUESTS, value = "10"),
                    // 同时有多少个线程可以调用兜底方法，默认10，超出就会报错，在服务隔离时设置使用
                    @HystrixProperty(name = HystrixPropertiesManager.FALLBACK_ISOLATION_SEMAPHORE_MAX_CONCURRENT_REQUESTS, value = "10")
            }
    )
    @GetMapping("/test/semaphore/{id}")
    public String testSemaphore(@PathVariable("id") long id) throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        Thread.sleep(200);
        log.info("入参为：{}，线程池：{}", id, threadName);
        return "入参为：" + id + " 线程池：" + threadName;
    }

    @GetMapping(value = "/consumer/payment/getById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") long id) {
        log.info("getById payment start");
        return restTemplate.getForObject(PAYMENT_URL + "/payment/getById/" + id, CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/getPortLB/{id}")
    public String getPortLB(@PathVariable("id") long id) {
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("PAYMENT-SERVICE");
        if (serviceInstanceList == null || serviceInstanceList.size() <= 0) {
            return "error";
        }
        ServiceInstance serviceInstance = loadBalancer.instance(serviceInstanceList);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/getById/" + id, String.class);
    }

    @GetMapping("/getResult/{id}")
    public String getResult2(@PathVariable("id") long id) {
        String threadName = Thread.currentThread().getName();
        log.info("入参为：{}，线程池：{}", id, threadName);
        return "入参为：" + id + " 线程池：" + threadName;
    }

    public String fallbackMethod(long id) {
        return "服务器忙，请稍后再试！";
    }
}
