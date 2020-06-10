package com.huanzi.juc.code.atomic.AtomicArray;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @program: Multithreading-study
 * @description:
 * @author: cheH
 * @create: 2020-06-05 10:52
 * @email: 18271645764@163.com
 **/
public class IntegerArray {
    public static void main(String[] args) {
        AtomicIntegerArray array = new AtomicIntegerArray(10);
        int length = array.length();
        int i = array.get(5);
        array.set(1,15);//通知jmm刷到主存去


        array.getAndSet(3,10);

    }
}
