package com.huanzi.design_pattern.chapter7_IMMUTABLE_DESIGN_PATTERN;

/**
 * @program: Multithreading-study
 * @description: 不可变对象测试
 * @author: cheH
 * @create: 2020-06-02 22:32
 * @email: 18271645764@163.com
 * 单线程结果
 * //        //57725
 * //        SynObj synObj = new SynObj();
 * //        synObj.setName("Alex");
 *         //49840
 *
 *
 **/


public class ImmutableTest {
    public static void main(String[] args) throws InterruptedException {
        long startTimeStamp = System.currentTimeMillis();
        //1395 不可变对象,效率较高
        ImmutableObj alex = new ImmutableObj("Alex");
        //1688
        SynObj synObj = new SynObj();
        synObj.setName("Alex");
        Thread t1 = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100_000; i++) {
                    System.out.println(synObj.toString());
                }
            }
        };
        t1.start();
        Thread t2 = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100_000; i++) {
                    System.out.println(synObj.toString());
                }
            }
        };
        t2.start();
        t1.join();
        t2.join();
        long endTimeStamp = System.currentTimeMillis();
        System.out.println("Elapsed time:"+(endTimeStamp-startTimeStamp));
    }
}

final class ImmutableObj{
    private  final String name;

    public ImmutableObj(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ImmutableObj{" +
                "name='" + name + '\'' +
                '}';
    }
}

class SynObj{
    private String name;

    public synchronized void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SynObj{" +
                "name='" + name + '\'' +
                '}';
    }
}
