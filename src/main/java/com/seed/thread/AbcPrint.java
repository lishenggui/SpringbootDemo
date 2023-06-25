package com.seed.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AbcPrint {
    private  static volatile int state = 1;
    private static AtomicInteger astate = new AtomicInteger(1);

    public static void main(String[] args) {

        final Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println("A0");
            }
        }, "T1");
        final Thread t2 = new Thread(new Runnable() {
            public void run() {

                try {
                    t1.join();
                    System.out.println("B0");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T2");
        final Thread t3 = new Thread(new Runnable() {
            public void run() {
                try {
                    t2.join();
                    System.out.println( "C0");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T3");
        t1.start();
        t2.start();
        t3.start();
//        方法二
         ExecutorService executor = Executors.newFixedThreadPool(100);

        /**
         * 基于synchronized
         */

        executor.execute(()->{
             ABC1.printA();
         });
        executor.execute(()->{
            try {
                ABC1.printB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.execute(()->{
            try {
                ABC1.printC();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        executor.execute(()->{
            ABC2.printA();
        });
        executor.execute(()->{
            ABC2.printB();
        });
        executor.execute(()->{
            ABC2.printC();
        });

        executor.execute(()->{
            ABC3.printA();
        });
        executor.execute(()->{
            ABC3.printB();
        });
        executor.execute(()->{
            ABC3.printC();
        });

        executor.execute(()->{
            ABC4.printA();
        });
        executor.execute(()->{
            ABC4.printB();
        });
        executor.execute(()->{
            ABC4.printC();
        });


        executor.execute(()->{
            ABC5.printA();
        });
        executor.execute(()->{
            ABC5.printB();
        });
        executor.execute(()->{
            ABC5.printC();
        });

        executor.execute(()->{
            ABC6.printA();
        });
        executor.execute(()->{
            ABC6.printB();
        });
        executor.execute(()->{
            ABC6.printC();
        });


        executor.execute(()->{
            ABC7.printA();
        });
        executor.execute(()->{
            ABC7.printB();
        });
        executor.execute(()->{
            ABC7.printC();
        });


        executor.shutdown();
    }

    public static class ABC1 {
        private  final  static Object lock = new Object();
        private  static boolean bRun = false;
        private  static boolean cRun = false;

        public static  void printA(){
            synchronized (lock){
                System.out.println("A1");
                 bRun = true;
                 lock.notifyAll();
            }

        }
        public static  void printB() throws InterruptedException {
            synchronized (lock){
                if(!bRun){
                    lock.wait();
                }
                System.out.println("B1");
                cRun = true;
                lock.notifyAll();
            }

        }
        public static  void printC() throws InterruptedException {
            synchronized (lock){
                if(!cRun){
                    lock.wait();
                }
                System.out.println("C1");
            }
        }

    }

    /**
     * lock
     */
    public static class ABC2 {
        private  final  static Lock lock = new ReentrantLock();
        private  static int state = 1;

        public static  void printA(){
            synchronized (lock){
                if(state<4){
                    try {
                        lock.lock();
                        while (state==1){
                            System.out.println("A2");
                            state++;
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            }

        }
        public static  void printB(){
            synchronized (lock){
                if(state<4){
                    try {
                        lock.lock();
                        while (state==2){
                            System.out.println("B2");
                            state++;
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            }

        }
        public static  void printC() {
            synchronized (lock){
                if(state<4){
                    try {
                        lock.lock();
                        while (state==3){
                            System.out.println("C2");
                            state++;
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

    }

    /**
     * volatile
     */
    public static class  ABC3{
        public  static void printA(){
            while (state !=1){
            }
            System.out.println("A3");
            state++;
        }
        public  static void printB(){
            while (state !=2){

            }
            System.out.println("B3");
            state++;
        }
        public  static void printC(){
            while (state !=3){
            }
            System.out.println("C3");
            state++;
        }
    }

    /**
     * automicInteger
     */
    public static class  ABC4{
        public  static void printA(){
            while (astate.get()!=1){

            }
            System.out.println("A4");
            astate.incrementAndGet();
        }
        public  static void printB(){
            while (astate.get()!=2){

            }
            System.out.println("B4");
            astate.incrementAndGet();
        }
        public  static void printC(){
            while (astate.get()!=3){
            }
            System.out.println("C4");
            astate.incrementAndGet();
        }
    }

    /**
     * lock+condition
     */
    public static class  ABC5{
        private  static  Lock lock = new ReentrantLock();
        private  static  int cstate = 1;
        private  static Condition a = lock.newCondition();
        private  static Condition b = lock.newCondition();
        private  static Condition c = lock.newCondition();
        public  static void printA()  {
            while (cstate<4){
                try {
                    lock.lock();
                    while (cstate!=1){
                       a.await();
                    }
                    System.out.println("A5");
                    cstate++;
                    b.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
        public  static void printB() {
            while (cstate<4){
                try {
                    lock.lock();
                    while (cstate!=2){
                        b.await();
                    }
                    System.out.println("B5");
                    cstate++;
                    c.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
        public  static void printC()  {
            while (cstate<4){
                try {
                    lock.lock();
                    while (cstate!=3){
                        c.await();
                    }
                    System.out.println("C5");
                    cstate++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
    }

    /**
     * semaphore
     */
    public static class  ABC6{
        private  static  int cstate = 1;
        //申请一个许可
        private  static Semaphore semaphore = new Semaphore(1);
        private  static int state = 1;
        public  static void printA()  {
            while (cstate<4){
                try {
                    while (state==1){
                        semaphore.acquire();
                        System.out.println("A6");
                        semaphore.release();
                        state++;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        public  static void printB()  {
            while (cstate<4){
                try {
                    while (state==2){
                        semaphore.acquire();
                        System.out.println("B6");
                        semaphore.release();
                        state++;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        public  static void printC()  {
            while (cstate<4){
                try {
                    while (state==3){
                        semaphore.acquire();
                        System.out.println("C6");
                        semaphore.release();
                        state++;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * CountDownLatch
     */
    public static class  ABC7{
        //申请一个许可
        private  static CountDownLatch countDownLatchB = new CountDownLatch(1);
        private  static CountDownLatch countDownLatchC = new CountDownLatch(1);
        public  static void printA()  {
                 System.out.println("A7");
                 countDownLatchB.countDown();
            }
        public  static void printB()  {
            try {
                countDownLatchB.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B7");
                countDownLatchC.countDown();
            }
        public  static void printC()  {
            try {
                countDownLatchC.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("C7");
        }

    }
}
