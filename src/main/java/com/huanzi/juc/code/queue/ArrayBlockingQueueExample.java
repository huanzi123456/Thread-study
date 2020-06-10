package com.huanzi.juc.code.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @program: Multithreading-study
 * @description:
 * @author: cheH
 * @create: 2020-06-05 18:40
 * @email: 18271645764@163.com
 **/
public class ArrayBlockingQueueExample {
    /**
     * FILO
     * @param size
     * @param <T>
     * @return
     */
    public <T> ArrayBlockingQueue<T> create(int size){
        return new ArrayBlockingQueue<>(size);
    }



}
