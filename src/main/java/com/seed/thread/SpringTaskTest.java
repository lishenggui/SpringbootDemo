package com.seed.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

public class SpringTaskTest {
    public static void main(String[] args) throws Exception {
        //创建带线程池的调度器
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        //手动初始化
        taskScheduler.initialize();
        //设置线程池
        taskScheduler.setPoolSize(30);
        System.out.println(taskScheduler);

        taskScheduler.schedule(()->{
                System.out.println("sssss");
        },new CronTrigger("0/1 * * * * *"));//这串字符串是cron表达式 代表每隔5秒执行一次
    }

}
