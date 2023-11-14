package com.seed.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class DirectReceiver {

    @RabbitListener(queues = "TestDirectQueue")
    public void process1(Object testMessage) {
        System.out.println("DirectReceiver消费者收到消息  : " + testMessage.toString());
    }

    @RabbitListener(queues = "AckDirectQueue")
    public void process2(Message message, Channel channel) throws IOException {
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        /**
         * 模拟重试三次，前三次都拒绝，，回退到队列，第四次消费成功
         */
      if(deliveryTag>3){
          channel.basicAck(deliveryTag,false);
          log.info("消费者收到消息：{}",message);
      }else {
          /**
           *投递序号
           *是否批量
           *是否重新放回队列
           */
          channel.basicNack(deliveryTag,false,true);
          log.info("消费者消费失败消息，回退到队列：{}",message);

      }
    }

}