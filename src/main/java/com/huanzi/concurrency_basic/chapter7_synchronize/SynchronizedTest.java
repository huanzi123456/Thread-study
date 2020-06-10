package com.huanzi.concurrency_basic.chapter7_synchronize;

public class SynchronizedTest {
	private static final Object LOCK = new Object();

	//jstack
	public static void main(String[] args) {
		Runnable runnable = SynchronizedTest::run;
		Thread t1 = new Thread(runnable);
		Thread t2 = new Thread(runnable);
		Thread t3 = new Thread(runnable);
		t1.setName("t1");
		t2.setName("t2");
		t3.setName("t3");

		t1.start();
		t2.start();
		t3.start();
	}

	private static void run() {
		//串行化执行
		synchronized (LOCK) {
			try {
				Thread.sleep(2_000);
				String name = Thread.currentThread().getName();
				System.out.println(name);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
