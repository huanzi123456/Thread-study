package com.huanzi.concurrency_basic.chapter3_stackSize;

import java.util.Arrays;

/**
 * ThreadGroup
 * 		没有传，Thread默认获取父线程的ThreadGroup作为该线程的ThreadGroup
 * 		ThreadGroup.activeCount()
 * 		threadGroup.enumerate(threads);
 *
 */
public class CreateThread2 {
	public static void main(String[] args) {
		Thread t = new Thread(()->{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		t.start();
//		//java.lang.ThreadGroup[name=main,maxpri=10]
//		System.out.println(t.getThreadGroup());

//		//main
//		System.out.println(Thread.currentThread().getName());

//		//java.lang.ThreadGroup[name=main,maxpri=10]
//		System.out.println(Thread.currentThread().getThreadGroup());

		ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
		//获取活跃线程数
		System.out.println(threadGroup.activeCount());

		Thread[] threads = new Thread[threadGroup.activeCount()];
		threadGroup.enumerate(threads);

		Arrays.asList(threads).forEach(System.out::println);
		//Thread[main,5,main]
		//Thread[Monitor Ctrl-Break,5,main]
		//Thread[Thread-0,5,main]
	}
}
