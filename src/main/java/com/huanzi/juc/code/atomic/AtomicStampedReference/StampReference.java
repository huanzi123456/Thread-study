package com.huanzi.juc.code.atomic.AtomicStampedReference;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @program: Multithreading-study
 * @description:
 * @author: cheH
 * @create: 2020-06-05 10:40
 * @email: 18271645764@163.com
 *
 * 解决 aba问题
 * 携带版本
 **/
public class StampReference {
    private static AtomicStampedReference<Integer> atomic = new AtomicStampedReference<>(100,0);
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                atomic.compareAndSet(100,101,atomic.getStamp(),atomic.getStamp()+1);
                atomic.compareAndSet(101,100,atomic.getStamp(),atomic.getStamp()+1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        Thread t2 = new Thread(() -> {
            try {
                int stamp = atomic.getStamp();
                System.out.println("Before sleep stamp :"+stamp);
                TimeUnit.SECONDS.sleep(2);
                atomic.compareAndSet(100,101,stamp,stamp+1);
                atomic.compareAndSet(101,100,stamp,stamp+1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
