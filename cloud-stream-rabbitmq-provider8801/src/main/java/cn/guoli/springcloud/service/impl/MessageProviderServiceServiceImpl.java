package cn.guoli.springcloud.service.impl;

import cn.guoli.springcloud.service.MessageProviderService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 发送消息  @EnableBinding(Source.class) == 定义消息的推送管道
 *
 * @Author guoli
 * @Since 2021-02-02 23:07
 */
@EnableBinding(Source.class)
public class MessageProviderServiceServiceImpl implements MessageProviderService {
    /**
     * 消息发送管道
     */
    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        return "发送成功-";
    }
}
