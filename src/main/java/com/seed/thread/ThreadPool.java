package com.seed.thread;

import java.util.concurrent.*;

public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ExecutorService singleExecutor = Executors.newSingleThreadExecutor();
        ExecutorService cacheExecutor = Executors.newCachedThreadPool();
        ScheduledExecutorService scheduleExecutor = Executors.newScheduledThreadPool(3);
        executorService.execute(()->{
            System.out.println("11111");
        });

        Future future = singleExecutor.submit(()->{
            return 1;
        });
        scheduleExecutor.schedule(()->{
            System.out.println("schedule start...");
        },5,TimeUnit.SECONDS);
        ExecutorService customExecutor = new ThreadPoolExecutor(2, 4, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"t_pool_"+r.hashCode());
            }
        },new ThreadPoolExecutor.AbortPolicy());


    }
}
