package com.huanzi.concurrency_basic.chapter12_threadGroup;

import java.util.Arrays;

/**
 * 1.5api之前  ThreadGroupAPI管理thread
 *		自行查看api
 */
public class ThreadGroupAPI {
	public static void main(String[] args) throws InterruptedException {
		ThreadGroup tg1 = new ThreadGroup("TG1");
		Thread t = new Thread(tg1,"t1"){
			@Override
			public void run() {
				try {
					System.out.println(tg1.getName());
					System.out.println(tg1.getParent().getName());
					Thread.sleep(5_000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();

//		tg1.setDaemon(true);   自动destory , 当最后一个线程完结的时候
		Thread.sleep(2_000);
		System.out.println(tg1.activeCount());
		System.out.println(tg1.isDestroyed());
		//确保没有活跃的线程,才能调用不然报错
//		tg1.destroy();

		System.out.println("=========================");
		Thread[] threads = new Thread[tg1.activeCount()];
		tg1.enumerate(threads);
		Arrays.asList(threads).forEach(System.out::println);

		//todo ???
		tg1.interrupt();
	}


}
