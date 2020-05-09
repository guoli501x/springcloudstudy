package cn.guoli.springcloud.dao;

import cn.guoli.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 功能描述
 *
 * @author guoli
 * @Date 2020/4/18 1:15
 */
@Mapper
public interface PaymentDao {
    int addPayment(Payment payment);

    Payment getPaymentById(@Param("id") long id);
}
