package com.seed.mq.rockmq;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "rocketmq发送")
public class MqProduct {
    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @GetMapping("/send")
    @ApiOperation("简单发送")
    public String send(@RequestParam("message") String message) {
        rocketMQTemplate.convertAndSend("topic-demo:tag-demo", "你好,Java发送内容为：" + message);
        return "ok";
    }

    @GetMapping("/sendOrder")
    @ApiOperation("顺序消息")
    public String sendOrder() {
        //参数一：topic   如果想添加tag,可以使用"topic:tag"的写法
        //参数二：消息内容
        //参数三：hashKey 用来计算决定消息发送到哪个消息队列， 一般是订单ID，产品ID等
        rocketMQTemplate.syncSendOrderly("topic-demo:tag-demo","111111创建","111111");
        rocketMQTemplate.syncSendOrderly("topic-demo:tag-demo","111111支付","111111");
        rocketMQTemplate.syncSendOrderly("topic-demo:tag-demo","111111完成","111111");
        rocketMQTemplate.syncSendOrderly("topic-demo:tag-demo","222222创建","222222");
        rocketMQTemplate.syncSendOrderly("topic-demo:tag-demo","222222支付","222222");
        rocketMQTemplate.syncSendOrderly("topic-demo:tag-demo","222222完成","222222");
        return "ok";
    }

    @GetMapping("/syncSend")
    @ApiOperation("同步消息")
    public String syncSend(@RequestParam("message") String message) throws InterruptedException {
        SendResult sendResult =  rocketMQTemplate.syncSend("topic-demo:tag-demo", "你好,Java发送内容为：" + message);
        System.out.println("发送同步消息结束："+sendResult);
        // 阻塞等待，保证消费
//        new CountDownLatch(1).await();
        return "ok";
    }

    @GetMapping("/ayncSend")
    @ApiOperation("异步消息")
    public String asyncSend(@RequestParam("message") String message) {
        rocketMQTemplate.asyncSend("topic-demo:tag-demo", "你好,Java发送内容为：" + message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("发送消息成功");
            }
            @Override
            public void onException(Throwable throwable) {
                System.out.println("发送消息失败:"+throwable.getMessage());
            }
        });
        return "ok";
    }


    @RequestMapping(value = "/sendOneWay", method = RequestMethod.GET)
    @ApiOperation("异步消息")
    public void sendOneWay() {
        rocketMQTemplate.sendOneWay("topic-demo:tag-demo", "发送单向消息");
    }

//    发送延时消息
    //    延时等级1到16分别表示 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h,3表示延迟10s发送
    @RequestMapping(value = "/sendDelay", method = RequestMethod.GET)
    @ApiOperation("延迟消息")
    public void sendDelay() {
        rocketMQTemplate.syncSend("topic-demo:tag-demo", MessageBuilder.withPayload("发送延迟10s消息").build(),15000,3);
    }

    @RequestMapping(value = "/sendBatch", method = RequestMethod.GET)
    @ApiOperation("批量消息")
    public void sendBatch() {
        List<Message> msgs = new ArrayList<Message>();
        for (int i = 0; i < 10; i++) {
            msgs.add(MessageBuilder.withPayload("Hello RocketMQ Batch Msg#" + i).
                    setHeader(RocketMQHeaders.KEYS, "KEY_" + i).build());
        }
        rocketMQTemplate.syncSend("topic-demo:tag-demo", msgs);
    }

    @RequestMapping(value = "/sendTransaction", method = RequestMethod.GET)
    @ApiOperation("事务消息")
    public void sendTransaction() {

        for (int i = 0; i < 10; i++) {
            Message msg = MessageBuilder.withPayload("rocketMQTemplate transactional message " + i).
                    setHeader(RocketMQHeaders.TRANSACTION_ID, "KEY_" + i).build();
            SendResult sendResult = rocketMQTemplate.sendMessageInTransaction(
                    "topic-demo:tag-demo",msg, null);
            System.out.printf("------rocketMQTemplate send Transactional msg body = %s , sendResult=%s %n",
                    msg, sendResult.getSendStatus());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
