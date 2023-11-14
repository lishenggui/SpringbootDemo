package com.seed.mq.rabbitmq;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : JCccc
 * @CreateTime : 2019/9/3
 * @Description :
 **/
@RestController
@Api(tags = "rabbitmq发送")
public class SendMessageController {

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @GetMapping("/sendDirectMessage/{message}")
    @ApiOperation("直接消息发送")
    public String sendDirectMessage(@PathVariable String message) {
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", message);
        return "ok";
    }

    @GetMapping("/sendTTLMessage/{message}")
    @ApiOperation("死信队列发送")
    public String sendDeadLetterMessage(@PathVariable String message) {
        String messageData = "延迟 10s ttl message,为："+message;
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("X", "XA", messageData);
        String messageData1 = "延迟 40s ttl message,为："+message;
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("X", "XB", messageData1);
        return "ok";
    }

    @GetMapping("/sendTTLMessage2/{message}/{delay}")
    @ApiOperation("死信队列自定义延迟发送")
    public String sendDeadLetterMessage(@PathVariable String message,@PathVariable String delay) {
        String messageData = "延迟 "+delay+" ttl message,为："+message;
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("X", "XC", messageData,correlationData -> {
            correlationData.getMessageProperties().setExpiration(delay);
            return correlationData;
        });
        return "ok";
    }

    @GetMapping("/sendACKMessage/{message}")
    @ApiOperation("直接消息发送手动确认")
    public String sendACKMessage(@PathVariable String message) {
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("AckDirectExchange", "AckDirectRouting", message);
        return "ok";
    }


}
