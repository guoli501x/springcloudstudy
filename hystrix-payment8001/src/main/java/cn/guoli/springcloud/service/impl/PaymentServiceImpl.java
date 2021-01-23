package cn.guoli.springcloud.service.impl;

import cn.guoli.springcloud.service.PaymentService;
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

    @Override
    public String timeout(int id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程是： " + Thread.currentThread().getName() + "; timeout, id = " +id;
    }
}
