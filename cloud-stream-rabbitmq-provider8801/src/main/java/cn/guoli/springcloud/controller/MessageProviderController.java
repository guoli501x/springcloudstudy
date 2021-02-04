package cn.guoli.springcloud.controller;

import cn.guoli.springcloud.service.MessageProviderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 功能描述
 *
 * @Author guoli
 * @Since 2021-02-02 23:11
 */
@RestController
public class MessageProviderController {
    @Resource
    private MessageProviderService messageProviderService;

    @PostMapping("/send")
    public String send() {
        return messageProviderService.send();
    }
}
