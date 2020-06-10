package com.huanzi.juc.code.ThreadPool;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @program: Multithreading-study
 * @description:
 * @author: cheH
 * @create: 2020-06-08 22:57
 * @email: 18271645764@163.com
 **/
public class ExecutorsTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        ExecutorService service = Executors.newCachedThreadPool();
//        useCachedThreadPool();
//        Optional.of(Runtime.getRuntime().availableProcessors()).ifPresent(System.out::println);
//        ExecutorService service = useWorkStealingPool();

//        testInvokeAll();
//        testSubmitRunnable();
        testSubmit();
    }

    /**
     * {@link ExecutorService#execute(Runnable)}  无返回值
     */



    /**
     * @see ExecutorService#isShutdown()
     * {@link ThreadPoolExecutor#isTerminating()}  是否在终结中
     * ExecutorService
     *      isShutdown()
     *      showdown()    调用了之后,是否还可以执行一个runnable()
     *          rejectExecutionException
     *      isTerminated()
     *
     *blocked方法:
     * {@link ThreadPoolExecutor#invokeAll(Collection-->Callable)}
     *timeout  添加超时时间
     *
     * {@link ThreadPoolExecutor#invokeAny(Collection)}
     * timeout  添加超时时间  超时不执行
     * <T> T invokeAny(Collection<? extends Callable<T>> tasks,
     *                     long timeout, TimeUnit unit)
     *todo ???结果返回之后,其他的Callabla继续运行么   其他的线程被取消
     */
    public static void testInvokeAny() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> collect = IntStream.range(0, 5).boxed().map(
                i -> (Callable<Integer>) () -> {
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                    return i;
                }
        ).collect(toList());
        Integer integer = service.invokeAny(collect);
        System.out.println("=======================finish" +integer);
    }
    //invokeAll  blocked
    //todo 分三个阶段:  执行,获取(处理),输出    每一个阶段需要等待完成才能执行下一个阶段
    public static void testInvokeAll() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.invokeAll(
                IntStream.range(0, 5).boxed().map(
                        i -> (Callable<Integer>) () -> {
                            TimeUnit.SECONDS.sleep(5);
                            System.out.println(Thread.currentThread().getName() + " : " + i);
                            return i;
                        }
                ).collect(toList())).stream().map(
                        future ->{
                            try {
                               return future.get();
                            } catch (Exception e) {
                               throw new RuntimeException(e);
                        }
                }).forEach(System.out::println);
        System.out.println("=======================finish");
    }

    /**
     * @see Future#get 获取结果
     * @see Future#cancel(boolean)   cancel之后不能拿结果
     * 试图取消,可能失败(任务完成,)
     * <ul>
     *     <li>任务完成</li>
     * </ul>
     * {@link ExecutorService#submit(Runnable)}  返回future
     *
     *异步方法
     * {@link ExecutorService#submit(Runnable, Object)}
     * {@link ExecutorService#submit(Callable)}
     * 
     */
    private static void testSubmitRunnable() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<?> future = service.submit(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        Object o = future.get();
        System.out.println("Result : " + o);
    }

    private static void testSubmit() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        String result = "DONE";
        Future<String> submit = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, result);
        System.out.println(submit.get());
    }


    /**
     * scheduler 解决办法
     *      Timer/TimeTask
     *      ScheduledExecutorService
     *      crontab
     *      cron4j
     *      quartz   定时任务首选
     *延迟 {@link ScheduledExecutorService#schedule(Runnable, long, TimeUnit)} --> Future
     *todo 定时jdk Timer方式 {@link ScheduledExecutorService#scheduleAtFixedRate(Runnable, long, long, TimeUnit)} --> Future
     *future.cancel(true); 取消之后不会执行
     * 执行完之后在等两秒再去执行其他的任务
     * {@link ScheduledExecutorService#scheduleWithFixedDelay(Runnable, long, long, TimeUnit)}
     *
     * {@link ScheduledThreadPoolExecutor.DelayedWorkQueue#schedule(Runnable, long, TimeUnit)}
     *
     * 构造方法:
     * public ScheduledThreadPoolExecutor(int corePoolSize) {
     *         super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
     *               new DelayedWorkQueue());
     *     }-->ThreadPoolExecutor
     *
     */
    @SuppressWarnings("all")
    private static ScheduledExecutorService uesScheduled() {
        return Executors.newScheduledThreadPool(1);
    }

    /**
     * future  Callable
     * @param service
     * @throws InterruptedException
     */
    private static void futureAndCallable(ExecutorService service) throws InterruptedException {
        List<Callable<String>> collect = IntStream.range(0, 20).boxed().map(i -> (Callable<String>) () -> {
            System.out.println("Thread" + Thread.currentThread().getName());//4个线程
            sleep(2);
            return "Task-" + i;
        }).collect(toList());
        List<Future<String>> futures = service.invokeAll(collect);
        futures.stream().map(future ->{
            try {
               return future.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).forEach(System.out::println);
    }

    public static void sleep(long sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * public static ExecutorService newWorkStealingPool() {
     *         return new ForkJoinPool
     *             (Runtime.getRuntime().availableProcessors(),
     *              ForkJoinPool.defaultForkJoinWorkerThreadFactory,
     *              null, true);
     *     }
     * @see ForkJoinPool-->
     *      Runtime.getRuntime().availableProcessors() 获取cpu核树
     *      windows检查合数  dxdiag
     *
     */
    public static ExecutorService useWorkStealingPool() {
        return  Executors.newWorkStealingPool();
    }


    /**
     *  单个线程池与一个线程的区别
     *      单个线程直接会销毁,线程池会回收再利用
     *      单个线程没有任务队列,
     * @see Executors-->
     *      public static ExecutorService newSingleThreadExecutor() {
     *         return new FinalizableDelegatedExecutorService
     *             (new ThreadPoolExecutor(1, 1,
     *                                     0L, TimeUnit.MILLISECONDS,
     *                                     new LinkedBlockingQueue<Runnable>()));
     *     }
     *
     * @see Executors.FinalizableDelegatedExecutorService# -->
     *      包装类,只暴露ExecutorService的方法
     */
    @SuppressWarnings("all")
    public static void useSinglePool() {
        ExecutorService service = Executors.newSingleThreadExecutor();
    }

    /**
     * @see Executors-->
     * return new ThreadPoolExecutor(nThreads, nThreads,
     *                                       0L, TimeUnit.MILLISECONDS,
     *                                       new LinkedBlockingQueue<Runnable>());
     * @see LinkedBlockingQueue-->
     *            public LinkedBlockingQueue() {
     *                  this(Integer.MAX_VALUE);
 *                }
     * 先放到 闲暇的线程里面,然后没有多余线程了之后,会放到queue!
     * 不开辟新的线程!
     * 关闭需要显示调用 shutdown()
     */
    public static void useFixSizePool() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        IntStream.range(0,100).boxed().forEach(i->
                service.execute(()->{
                            try {
                                TimeUnit.SECONDS.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(Thread.currentThread().getName() + " [" + i + " ]");
                        }
                ));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(((ThreadPoolExecutor) service).getActiveCount());
    }

    /**
     * 该线程池典型的提升执行短生命周期异步任务程序的性能:短任务,不然会创建很多线程
     * 利用ThreadPoolExecutor
     * 0  - Integer.MAX_VALUE
     * 60L  秒            停止回收,自动销毁
     * SyschronousQueue  同一时间存放一个
     */
    public static void useCachedThreadPool() throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(((ThreadPoolExecutor) service).getActiveCount());
        service.execute(() -> {
            //todo 开启一个会先放入到queue中
            System.out.println("========================");
        });
        System.out.println(((ThreadPoolExecutor) service).getActiveCount());
        //todo 每创建一个任务,当前没有可用线程都会新创建一个线程
        //queue只能放一个

        IntStream.range(0,100).boxed().forEach(i->
                service.execute(()->{
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " [" + i + " ]");
                }
        ));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(((ThreadPoolExecutor) service).getActiveCount());
    }
}
