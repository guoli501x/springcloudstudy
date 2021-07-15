package cn.guoli.springcloud.service.impl;

import cn.guoli.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
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
    @HystrixCommand(
            // 兜底方法
            fallbackMethod = "timeoutHandle",
            commandProperties = {
                    // 超时时间设置，默认1000，单位ms
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "3000"),
                    // 是否开启超时设置，默认true
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_TIMEOUT_ENABLED, value = "true"),
                    // 是否开启兜底方法，默认true
                    @HystrixProperty(name = HystrixPropertiesManager.FALLBACK_ENABLED, value = "true")
            }
    )
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
    @HystrixCommand(
            // 兜底方法
            fallbackMethod = "paymentCircuitBreakerFallback",
            commandProperties = {
                    // 是否开启断路器，默认true
                    @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ENABLED, value = "true"),
                    // 服务熔断的最小请求次数，默认20
                    @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value = "0"),
                    // 断路器开启的请求错误率百分比，默认50，即失败率达到一半或以上
                    @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value = "50"),
                    // 服务熔断时间，默认5000ms
                    @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value = "5000"),
                    // 是否强制开启断路器，默认false
                    @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_FORCE_OPEN, value = "false"),
                    // 是否强制关闭断路器，默认false
                    @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_FORCE_CLOSED, value = "false"),
                    // 统计时间，默认10,000ms
                    @HystrixProperty(name = HystrixPropertiesManager.METRICS_ROLLING_STATS_TIME_IN_MILLISECONDS, value = "10000"),
                    // 桶的数量，默认10个，用来记录请求结果状态（成功、失败、超时、拒绝），必须被统计时间整除，建议数百到数千毫秒一个桶
                    @HystrixProperty(name = HystrixPropertiesManager.METRICS_ROLLING_STATS_NUM_BUCKETS, value = "10"),
                    // 统计结果（请求数、失败率等）刷新间隔时间，默认500ms
                    @HystrixProperty(name = HystrixPropertiesManager.METRICS_HEALTH_SNAPSHOT_INTERVAL_IN_MILLISECONDS, value = "500")
            }
    )
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
