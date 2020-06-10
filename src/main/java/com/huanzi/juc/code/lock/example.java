package com.huanzi.juc.code.lock;

/**
 * @program: Multithreading-study
 * @description:
 * @author: cheH
 * @create: 2020-06-05 14:13
 * @email: 18271645764@163.com
 **/

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁:
 * condition()
 *
 *
 *
 */
public class example {

    private static final  Lock lock = new ReentrantLock();

    //显示锁
    public static void main(String[] args) {

    }

    public static void needlock(){
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    //等价个于上一方法
    public static void needLockSync(){
        synchronized (example.class){
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
