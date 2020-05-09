package cn.guoli.springcloud.service.impl;

import cn.guoli.springcloud.dao.PaymentDao;
import cn.guoli.springcloud.entities.Payment;
import cn.guoli.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 功能描述
 *
 * @author guoli
 * @Date 2020/4/18 2:28
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int addPayment(Payment payment) {
        return paymentDao.addPayment(payment);
    }

    @Override
    public Payment getPaymentById(long id) {
        return paymentDao.getPaymentById(id);
    }
}
