package com.lagou.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

// Source.class⾥⾯就是对输出通道的定义（这是Spring Cloud Stream内置的通道封装）
@EnableBinding(Source.class)
public class MessageProducerImpl implements IMessageProducer {

    // 将MessageChannel的封装对象Source注⼊到这⾥使⽤
    @Autowired
    private Source source;

    @Override
    public void sendMessage(String context) {
        // 向mq中发送消息（并不是直接操作mq，应该操作的是spring cloud stream）

        // 使⽤通道向外发出消息(指的是Source⾥⾯的output通道)
        source.output().send(MessageBuilder.withPayload(context).build());
    }
}
