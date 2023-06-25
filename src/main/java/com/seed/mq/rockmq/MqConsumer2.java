package com.seed.mq.rockmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
//@RocketMQMessageListener(topic = "topic-demo", selectorExpression = "tag-demo", consumerGroup = "QueueHandler",messageModel = MessageModel.CLUSTERING)
public class MqConsumer2 implements RocketMQListener<MessageExt> , RocketMQPushConsumerLifecycleListener {
    @Override
    public void onMessage(MessageExt message) {
        String body = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("2接受消息内容:"+ body);
        log.info("2消息ID:"+message.getMsgId());
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.setInstanceName("consumer2");
    }
}
