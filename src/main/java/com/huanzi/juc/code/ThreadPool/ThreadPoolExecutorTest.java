package com.huanzi.juc.code.ThreadPool;

import java.util.concurrent.*;

/**
 * @program: Multithreading-study
 * @description:
 * @author: cheH
 * @create: 2020-06-08 17:50
 * @email: 18271645764@163.com
 *
 * 线程工具类: 结合线程里面的类!
 *      实现对线程的创建,管理!
 * @see ExecutorService
 * @see java.util.concurrent.Executor
 * @see java.util.concurrent.Executors
 *
 *
 **/
public class ThreadPoolExecutorTest {

    /**
     * 直接添加到 线程池队列里面不会直接执行
     * 需要一个启动信号
     * @param args
     */
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        executor.execute(()-> System.out.println(" a Runnable task is running!"));
        //当有任务运行的时候,会去队列里面拿
        executor.getQueue().add(()->{
            System.out.println("add a Runnable task to queue and run progress!");
        });
    }

    /**
     *api:
     *
     */
    public static void testApi() {
        ThreadPoolExecutor service = (ThreadPoolExecutor) buildThreadPoolExecutor();
        int activeCount = -1;
        int queueSize = -1;
        while (true){
            if (activeCount!=service.getActiveCount() || queueSize!=service.getQueue().size()){
                System.out.println(service.getActiveCount());
                System.out.println(service.getCorePoolSize());
                System.out.println(service.getQueue());
                System.out.println(service.getMaximumPoolSize());
                System.out.println("======================================");
            }
        }
    }

    private static void sleepSec(long sec){
        try {
            System.out.println("*  " + Thread.currentThread().getName()+"  *");
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * corePoolSize     核心数量
     * maximumPoolSize  最大线程池数量
     * keepAliveTime    存活时间
     * unit         时间单位
     * workQueues   工作队列
     * ThreadFactory
     * RejectedExecutionHandler handler  拒绝策略
     *      : DiscardPolicy  不执行任务
     *      : CallerRunsPolicy  谁提交谁运行
     *      :
     * @return
     */
    private static ExecutorService buildThreadPoolExecutor(){
        ExecutorService service = new ThreadPoolExecutor(
                1,1,30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(1),
                r->{ Thread t = new Thread(r); return t; }, //ThreadFactory接口
                new ThreadPoolExecutor.AbortPolicy());
        System.out.println("The ThreadPoolExecutor has been init");
        service.execute(() -> { sleepSec(1000); });
        service.execute(() -> { sleepSec(1000); });
        return service;
    }
}
