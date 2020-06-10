package com.huanzi.juc.code.ThreadPool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @program: Multithreading-study
 * @description:
 * @author: cheH
 * @create: 2020-06-09 23:38
 * @email: 18271645764@163.com
 *
 * 解决future模式的 get结果  阻塞问题?!
 *      只关注完成,不知道那个先完成,执行完需要自己去拿取(future.get() 阻塞的 )
 *      如果被打断之后,在shutdownNow()  拿到的只有未完成的 线程!
 *
 **/
public class CompletionServiceTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        futureTest2();
        futureTest3();
    }

    /**
     *缺点: 异步,
     *      阻塞住 future.get(); 没有回调
     */
    public static void futureTest() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> future = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });
        System.out.println("========================");
        future.get();
    }


    public static void futureTest2() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        final List<Callable<Integer>> callableList = Arrays.asList(() -> {
                sleep(10);
            System.out.println("The 10 finish");
            return 10;
            },() -> {
                sleep(5);
                System.out.println("The 20 finish");
            return 20;
            }
        );
        List<Future<Integer>> futures = service.invokeAll(callableList);
        Integer integer = futures.get(0).get();
        System.out.println(integer);

        Integer integer1 = futures.get(1).get();
        System.out.println(integer1);
    }
    //sleep method
    public static void sleep(long sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**todo
     * 可以利用runnable 拿取 相应的值
     * {@link ExecutorCompletionService#take()}
     * {@link ExecutorCompletionService#poll()}
     * {@link ExecutorCompletionService#poll(long, TimeUnit)}
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void futureTest3() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        final List<Callable<Integer>> callableList = Arrays.asList(() -> {
                    sleep(10);
                    System.out.println("The 10 finish");
                    return 10;
                },() -> {
                    sleep(5);
                    System.out.println("The 20 finish");
                    return 20;
                }
        );
        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(service);
        List<Future<Integer>> futures = new ArrayList<>();
        callableList.stream().forEach(
                callable ->{ futures.add(completionService.submit(callable)); }
        );
        Future<Integer> future =null;
        while ((future =completionService.take())!= null){
            System.out.println(future.get());
        }

    }



}
