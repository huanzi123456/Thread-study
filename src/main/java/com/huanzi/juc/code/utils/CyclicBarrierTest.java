package com.huanzi.juc.code.utils;

/**
 * @program: Multithreading-study
 * @description:
 * @author: cheH
 * @create: 2020-06-05 13:41
 * @email: 18271645764@163.com
 **/

import java.util.concurrent.CyclicBarrier;

/**
 *任务分片 :   阶段
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                //完结回调
            }
        });
    }
}
