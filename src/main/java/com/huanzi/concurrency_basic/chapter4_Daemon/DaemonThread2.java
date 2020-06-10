package com.huanzi.concurrency_basic.chapter4_Daemon;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程：
 * 		main线程
 *			t线程 ( 做事情的线程)
 *				innerThread线程
 *		设置守护线程可以正常结束application！
 */
public class DaemonThread2 {

	public static void main(String[] args) {
		System.out.println("main线程开始运行");
		Thread t = new Thread(()->{
			System.out.println("t线程开始运行");
			Thread innerThread = new Thread(()->{
				while (true){
					// only run a once
					try {
						System.out.println("Do something...");
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			innerThread.setDaemon(true);
			innerThread.start();
		});

		try {
			Thread.sleep(1_000);
			System.out.println("T thread finish done.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		t.setDaemon(true);
		t.start();
		System.out.println("main thread finish done.");
	}
}
