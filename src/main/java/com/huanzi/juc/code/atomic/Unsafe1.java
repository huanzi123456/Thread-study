package com.huanzi.juc.code.atomic;


import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: Multithreading-study
 * @description:
 * @author: cheH
 * @create: 2020-06-05 11:09
 * @email: 18271645764@163.com
 *
 * c++
 * 汇编   --->无锁,乐观锁
 *
 * 替代classLoader
 * 开辟内存,(绕过初始化)
 **/
public class Unsafe1 {

    //反射调用
    public static void main(String[] args) throws InterruptedException, NoSuchFieldException {
//        Unsafe unsafe = getUnsafe();
//        System.out.println(unsafe);


    }

    private static void getXiaohao() throws NoSuchFieldException, InterruptedException {
        /**
         *stupidCounter
         * Counter result 9830534
         * time spend result 593
         *
         * SyncCounter
         *Counter result 10000000
         * time spend result 848
         *
         * lockCounter
         *Counter result 10000000
         * time spend result 603
         *
         * AtomicCounter
         *Counter result 10000000
         * time spend result 737
         *
         *Cas
         * Counter result 0
         * time spend result 439
         */
        ExecutorService service = Executors.newFixedThreadPool(1000);
        Counter counter = new CasCounter();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            service.submit(new CounterRunnable(counter,10000));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);
        long end = System.currentTimeMillis();
        System.out.println("Counter result "+counter.getCounter());
        System.out.println("time spend result "+(end - start));
    }


    /**
     * 获取Unsafe
     * @return
     */
    private static Unsafe getUnsafe(){
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    static class StupidCounter implements Counter{
        private long count = 0;
        @Override
        public void increment() {
            count++;
        }

        @Override
        public long getCounter() {
            return count;
        }
    }

    static class SyncCounter implements Counter{
        private long count = 0;
        @Override
        public synchronized void increment() {
            count++;
        }

        @Override
        public synchronized long getCounter() {
            return count;
        }
    }

    static class CasCounter implements Counter{
        private volatile long count = 0;
        private Unsafe unsafe;
        private long offset;

        CasCounter() throws NoSuchFieldException {
            Unsafe unsafe = getUnsafe();
            offset = unsafe.objectFieldOffset(CasCounter.class.getDeclaredField("count"));
        }

        @Override
        public synchronized void increment() {
            long current = count;
            while (!unsafe.compareAndSwapLong(this,offset,current,current+1)){
                current =count;
            }
        }

        @Override
        public  long getCounter() {
            return count;
        }
    }

    static class AtomicCounter implements Counter{
        private AtomicLong count = new AtomicLong();
        @Override
        public synchronized void increment() {
            count.incrementAndGet();
        }

        @Override
        public synchronized long getCounter() {
            return count.get();
        }
    }

    static class LockCounter implements Counter{
        private long count = 0;
        //默认非公平锁
        private final Lock lock = new ReentrantLock();
        @Override
        public  void increment() {
            lock.lock();
            count++;
            lock.unlock();
        }

        @Override
        public synchronized long getCounter() {
            return count;
        }
    }

    interface Counter{
        void increment();
        long getCounter();
    }

    static class CounterRunnable implements Runnable{
        private final Counter counter;
        private final int num;

        public CounterRunnable(Counter counter, int num) {
            this.counter = counter;
            this.num = num;
        }

        @Override
        public void run() {
            for (int i = 0; i < num; i++) {
                counter.increment();
            }
        }
    }

}
