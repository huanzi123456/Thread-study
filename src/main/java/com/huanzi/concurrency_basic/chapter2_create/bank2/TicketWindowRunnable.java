package com.huanzi.concurrency_basic.chapter2_create.bank2;

import java.util.concurrent.TimeUnit;

public class TicketWindowRunnable implements Runnable {

	private int index = 1;

	private final static int MAX = 1000;

	public void run() {
		while (index <= MAX){
			System.out.println(Thread.currentThread() + "的号码是" + (index++));
			try {
				TimeUnit.SECONDS.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
