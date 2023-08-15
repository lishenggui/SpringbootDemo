package com.seed.mq.rockmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 如果是应答是RECONSUME_LATER。默认会重新投递15次
 */
public class HandCommitConsumer{
    public static void main(String[] args) throws InterruptedException, MQClientException {
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("transaction_consumer_test");
////        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
//        consumer.setNamesrvAddr("192.168.107.138:9876");
//        consumer.setInstanceName(UUID.randomUUID().toString());
//        consumer.subscribe("topic-demo", "tag-demo");
//        consumer.registerMessageListener(new MessageListenerConcurrently() {
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
//                                                            ConsumeConcurrentlyContext context) {
//                for (Message msg : msgs) {
//                    System.out.println(new String(msg.getBody()) + " === date:" + new Date());
//                }
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });
//
//        consumer.start();
//        System.out.println("start success1");

        DefaultMQPushConsumer consumer2 = new DefaultMQPushConsumer("transaction_consumer_test");
//        consumer2.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer2.setNamesrvAddr("192.168.107.138:9876");
        consumer2.setInstanceName(UUID.randomUUID().toString());
        consumer2.subscribe("topic-demo", "tag-demo2");
        consumer2.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                for (Message msg : msgs) {
                    System.out.println(new String(msg.getBody()) + " === date:" + new Date());
                }

                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        System.out.println("start success2");
        consumer2.start();
    }


}
