package cn.guoli.springcloud.service.impl;

import cn.guoli.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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
}
