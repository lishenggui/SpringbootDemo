package com.seed.thread;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableTest {

    @Data
    @AllArgsConstructor
    public static class User {
        String name;
        Integer age;
    }

    public static class UserTask implements Callable<User>{
        private  Integer age;

        public  UserTask(Integer age){
            this.age = age;
        }

        @Override
        public User call() throws Exception {
            String name = Thread.currentThread().getName();
            System.out.println("thread1:name:"+name);
            User user = new User(name,this.age+100);
            return user;
        }
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<User>>futureList = new ArrayList<>();
        for (int i = 0; i <20 ; i++) {
            UserTask userTask = new UserTask(i);
//            FutureTask<User>future = new FutureTask<>(userTask);
//            executorService.submit(future);
            Future<User>future = executorService.submit(userTask);
            futureList.add(future);
        }
        futureList.forEach(userFuture -> {
            try {
                System.out.println(userFuture.get().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

//        System.out.println(futureList.toArray());

    }
}
