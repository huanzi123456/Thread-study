package com.huanzi.juc.code.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @program: Multithreading-study
 * @description:
 * @author: cheH
 * @create: 2020-06-08 22:21
 * @email: 18271645764@163.com
 **/
public class ThreadPoolExecutorTask {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = new ThreadPoolExecutor(
                10,20,30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(1),
                r->{ Thread t = new Thread(r); return t; }, //ThreadFactory接口
                new ThreadPoolExecutor.AbortPolicy());
        IntStream.range(0,20).boxed().forEach(i ->
            service.execute( () -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " ["+i+"] finish done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            })
        );
        //有活着的线程,然后不会关闭程序
        service.shutdown();
        //todo shutdownNow();
//        service.shutdownNow();
        service.awaitTermination(1,TimeUnit.HOURS);  //等待一个小时知道完成才接着执行以下代码
        System.out.println("==================over===================");
        //非阻塞 do  other  work !

    }
}
