package cn.guoli.springcloud.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

/**
 * 发送消息  @EnableBinding(Source.class) == 定义消息的推送管道
 *
 * @Author guoli
 * @Since 2021-02-02 23:07
 */
@EnableBinding(Sink.class)
public class MessageConsumerServiceService {
    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        System.out.println("消费者8802 ====> 收到的消息：" + message.getPayload());
    }
}
