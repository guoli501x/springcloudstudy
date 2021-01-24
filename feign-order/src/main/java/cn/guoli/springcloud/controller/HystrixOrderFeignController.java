package cn.guoli.springcloud.controller;

import cn.guoli.springcloud.service.HystrixPaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 加上hystrix后测试
 * DefaultProperties注解 === 加载类上，配置全局兜底方法 @HystrixCommand 加载方法上
 *
 * @author guoli
 * @Date 2020/6/27 17:52
 */
@RestController
@Slf4j
@RequestMapping(value = "/hystrix/order/feign")
//@DefaultProperties(defaultFallback = "orderGlobalFallbackMethod")
public class HystrixOrderFeignController {
    @Resource
    private HystrixPaymentFeignService hystrixPaymentFeignService;

    /**
     * 测试feign的超时控制,openfeign默认1s
     * @param id id
     * @return 结果
     */
    @GetMapping("/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "testFeignTimeoutHandle", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
//    })
//    @HystrixCommand
    public String testFeignTimeout(@PathVariable("id") int id) {
        return hystrixPaymentFeignService.timeout(id);
    }

    public String testFeignTimeoutHandle(int id){
        return "消费方兜底方法  " + id;
    }

    /**
     * 全局fallback（兜底）方法
     * @return Global异常处理信息
     */
    public String orderGlobalFallbackMethod() {
        return "Global异常处理信息：请稍后再试--";
    }
}
