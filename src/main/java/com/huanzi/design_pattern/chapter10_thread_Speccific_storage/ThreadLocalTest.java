package com.huanzi.design_pattern.chapter10_thread_Speccific_storage;

import java.util.Random;

/**
 * 线程保险箱设计模式:
 *		ThreadLocal :
 *			两个线程会访问,	ThreadLocal  !但是他们的key不同,所以互相不可见
 *		以当前线程为key值
 */
public class ThreadLocalTest {

	private static ThreadLocal threadLocal = new ThreadLocal();

	private static Random random = new Random(System.currentTimeMillis());

	// JVM start main thread
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(() -> {
			threadLocal.set("Thread-T1");
			try {
				Thread.sleep(random.nextInt(1000));
				System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread t2 = new Thread(() -> {
			threadLocal.set("Thread-T2");
			try {
				Thread.sleep(random.nextInt(1000));
				System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("========================");
		System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
	}
}
