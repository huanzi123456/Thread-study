package com.huanzi.concurrency_basic.chapter2_create.bank2;

/**
 * Thread[2号窗口,5,main]的号码是271
 * Thread[1号窗口,5,main]的号码是271
 *
 * 出现重复数据的问题？！
 * 		两个线程同时读取到index,并且加1返回
 *
 * 共享资源被争抢
 */
public class Bank2 {
	public static void main(String[] args) {
		final TicketWindowRunnable ticketWindow = new TicketWindowRunnable();
		Thread t1 = new Thread(ticketWindow,"1号窗口");
		Thread t2 = new Thread(ticketWindow,"2号窗口");
		Thread t3 = new Thread(ticketWindow,"3号窗口");

		t1.start();
		t2.start();
		t3.start();
	}
}
