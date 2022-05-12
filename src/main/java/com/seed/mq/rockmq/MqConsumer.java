package com.seed.mq.rockmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
@RocketMQMessageListener(topic = "topic-demo", selectorExpression = "tag-demo", consumerGroup = "QueueHandler")
public class MqConsumer implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt message) {
        String body = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("接受消息内容:"+ body);
        log.info("消息ID:"+message.getMsgId());
    }
}
