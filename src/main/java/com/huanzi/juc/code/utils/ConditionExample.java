package com.huanzi.juc.code.utils;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: Multithreading-study
 * @description:
 * @author: cheH
 * @create: 2020-06-05 15:16
 * @email: 18271645764@163.com
 **/

/**
 * lock -->wait  (signalled   interrupted)
 * condition
 *
 */
public class ConditionExample {

    private final static ReentrantLock lock = new ReentrantLock();

    private final static Condition condition = lock.newCondition();

    private static int data = 0;

    private static volatile boolean noUse = true;

    private static void buildData(){
        try {
            lock.lock();  //== sync  monitorEnter
            while (noUse){
                condition.await(); //== monitor.await()
            }
            data++;
            Optional.of("P:"+data).ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(1);
            noUse = true;
            condition.signalAll();   //monitor.notify()
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();       //sync monitorExit
        }
    }

    private static void useData(){
        try {
            lock.lock();
            while (!noUse){
                condition.await();
            }
            TimeUnit.SECONDS.sleep(1);
            Optional.of("C:"+data).ifPresent(System.out::println);
            noUse= false;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(()->{
            for (;;){
                buildData();
            }
        }).start();

        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (;;){
                    useData();
                }
            }).start();
        }
    }
}
