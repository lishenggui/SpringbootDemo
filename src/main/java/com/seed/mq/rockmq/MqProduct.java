package com.seed.mq.rockmq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqProduct {
    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @GetMapping("/send")
    public String get(@RequestParam("message") String message) {
        rocketMQTemplate.convertAndSend("topic-demo:tag-demo", "你好,Java发送内容为：" + message);
        return "ok";
    }
}
