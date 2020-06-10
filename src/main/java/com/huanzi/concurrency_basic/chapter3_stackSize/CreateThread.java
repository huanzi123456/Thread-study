package com.huanzi.concurrency_basic.chapter3_stackSize;

/**
 * Initializes a Thread with the current AccessControlContext.
 *  group   runnable  name  stackSize
 * @see java.lang.Thread#init(java.lang.ThreadGroup, java.lang.Runnable, java.lang.String, long)
 * name : thread- + nextThreadNum()；
 *		private static int threadInitNumber;
 * 		private static synchronized int nextThreadNum() {
 *         return threadInitNumber++;
 *     }
 *
 * start0()--> run()  判断targer是否为null, 为null不做任何事情！
 */
public class CreateThread {
	public static void main(String[] args) {
		Thread t1 = new Thread();
		Thread t2 = new Thread(() -> System.out.println("t2==================="));
		t1.start();
		t2.start();
		System.out.println(t1.getName());
		System.out.println(t2.getName());

		// 取名之后不会调用nextThreadID()
		Thread t3 = new Thread("MyName");
		Thread t4 = new Thread(()-> System.out.println("Runnable...."));

		System.out.println(t3.getName());
		System.out.println(t4.getName());

		Thread t5 = new Thread(()-> System.out.println("Runnable 5:" + Thread.currentThread().getName()),"Runnable Thread");
		t5.start();
	}
}
