package cn.guoli.springcloud.service;

import cn.guoli.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * 功能描述
 *
 * @author guoli
 * @Date 2020/4/18 2:25
 */
public interface PaymentService {
    int addPayment(Payment payment);

    Payment getPaymentById(@Param("id") long id);
}
