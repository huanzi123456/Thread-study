package com.huanzi.classloader.lang;

/**
 * @program: Multithreading-study
 * @description: classloader验证
 * @author: cheH
 * @create: 2020-06-03 22:25
 * @email: 18271645764@163.com
 *
 * java
 * jpg
 * exe
 * 不同文件的魔数不同: 验证:   主要是为安全考虑
 * 编译版本
 *
 * 类加载的三个阶段:
 **/
public class String {
    //类加载 链接:准备阶段就已经为其分配了内存
    private static int i=1;

    private Object object = new Object();

    private int x;

    public  String(){
        x = 10;
    }

    private int getValue(){
        return 1;
    }
}
