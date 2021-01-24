/*
 * Copyright (c) 2020-2020 guoli.Co.Ltd. All rights reserved.
 */

package cn.guoli.springcloud.service.impl;

import cn.guoli.springcloud.service.HystrixPaymentFeignService;
import org.springframework.stereotype.Component;

/**
 * 功能描述
 *
 * @Author guoli
 * @Since 2021-01-24 1:21
 */
@Component
public class HystrixPaymentFeignServiceImpl implements HystrixPaymentFeignService {
    @Override
    public String timeout(int id) {
        return "系统繁忙，请稍后再试";
    }
}
