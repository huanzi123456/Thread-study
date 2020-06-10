package com.huanzi.juc.code.atomic.AtomicReference;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: Multithreading-study
 * @description:
 * @author: cheH
 * @create: 2020-06-05 10:09
 * @email: 18271645764@163.com
 *
 * cas  <-> ABA问题
 *
 **/
public class Reference {
    public static void main(String[] args) {
        AtomicReference<Simple> atomic = new AtomicReference<>(new Simple("ALEX",12));
        System.out.println(atomic.get());
        boolean b = atomic.compareAndSet(new Simple("sdas", 15), new Simple("sdas", 22));
        System.out.println(b);
    }

    static class Simple{
        private String name;
        private int age;

        public Simple(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
