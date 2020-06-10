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
public class CountDownLatchApi {
    public static void main(String[] args) throws InterruptedException {
      final  CountDownLatch latch = new CountDownLatch(0); //>=0

        //参数: 等待时间
        latch.await();

    }
}
