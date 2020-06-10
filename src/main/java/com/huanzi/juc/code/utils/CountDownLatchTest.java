package com.huanzi.juc.code.utils;

/**
 * @program: Multithreading-study
 * @description:
 * @author: cheH
 * @create: 2020-06-05 12:27
 * @email: 18271645764@163.com
 **/

import java.util.Random;
import java.util.concurrent.*;

/**
 * java并发包工具:CountDownLatch 阻塞并发!
 */
public class CountDownLatchTest {
    //添加随机
    private static Random          random   = new Random(System.currentTimeMillis());
    private static ExecutorService service =  Executors.newFixedThreadPool(2);

    private static final CountDownLatch latch = new CountDownLatch(6);

    public static void main(String[] args) throws InterruptedException {
        //todo  总体串行化运行,但是有可以并行化运行的,可以使用countdownlatch
        //查询数据
        int[] data = query();
       //数据处理
        for (int i = 0; i < data.length; i++) {
            service.execute(new SimpleRunnable(data,i,latch)
            );
        }
//       blockThread();
        latch.await();
        //最终操作
        System.out.println("all of work finish done.");
        service.shutdown();
    }

    private static void blockThread() throws InterruptedException {
        //异步
        service.shutdown();
        //todo block住
        service.awaitTermination(1, TimeUnit.HOURS);
    }

    static class SimpleRunnable implements Runnable{
        private final int[] data;
        private final int index;

        //利用countdownlatch阻塞线程
        private final CountDownLatch latch;
        public SimpleRunnable(int[] data,int index,CountDownLatch latch) {
            this.data = data;
            this.index = index;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int value = data[index];
            if (value%2==0){
                data[index]=value*2;
            }else {
                data[index] = value * 10;
            }
            System.out.println(Thread.currentThread().getName() + "finished.");
            latch.countDown();
        }
    }

    private static int[] query(){
        return new int[]{1,3,5,7,9,11};
    }

}
