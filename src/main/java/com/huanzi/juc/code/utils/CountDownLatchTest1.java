package com.huanzi.juc.code.utils;

/**
 * @program: Multithreading-study
 * @description:
 * @author: cheH
 * @create: 2020-06-05 12:27
 * @email: 18271645764@163.com
 **/

import java.util.concurrent.CountDownLatch;

/**
 * java并发包工具:CountDownLatch 阻塞并发!
 */
public class CountDownLatchTest1 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(){
            @Override
            public void run() {
                System.out.println("do some initial working");
                try {
                    Thread.sleep(1000);
                    latch.await();
                    System.out.println("do other  working");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        //准备数据
        new Thread(){
            @Override
            public void run() {
                System.out.println("asyn perpare some data.");
                try {
                    Thread.sleep(2000);
                    System.out.println("data prepare done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
            }
        }.start();
        Thread.currentThread().join();
    }

}
