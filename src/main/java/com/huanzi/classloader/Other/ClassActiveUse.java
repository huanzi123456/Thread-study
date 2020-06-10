package com.huanzi.classloader.Other;

/**
 * @program: Multithreading-study
 * @description: 主动使用
 * @author: cheH
 * @create: 2020-06-03 22:48
 * @email: 18271645764@163.com
 **/
public class ClassActiveUse {
    public static void main(String[] args) {
        Obj obj = new Obj();
    }
}
class Obj{
    static {
        System.out.println("obj被初始化");
    }
}
