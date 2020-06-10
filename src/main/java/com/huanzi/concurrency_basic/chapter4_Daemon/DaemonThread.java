package com.huanzi.concurrency_basic.chapter4_Daemon;

/**
 * 设置为守护线程
 */
public class DaemonThread {
	public static void main(String[] args) {
		Thread t = new Thread(() -> {
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("done.");
		});

		// 设置为守护线程，需要在开始之前！
		// 守护线程随着所有活动的的线程销毁而被销毁
		// 避免有些线程一直活动，导致jvm不能退出
		t.setDaemon(true);

		t.start();

/**
 * main线程   -------------------------------
 * 		-> daemon() 守护线程
 */
	}
}
