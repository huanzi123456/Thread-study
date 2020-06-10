package com.huanzi.concurrency_basic.chapter2_create.bank1;

/**
 * 顺序的问题？？
 * 		共享资源的问题！
 */
public class Bank1 {
	public static void main(String[] args) {
		TicketWindowThread t1 = new TicketWindowThread("1号柜台");
		TicketWindowThread t2 = new TicketWindowThread("2号柜台");
		TicketWindowThread t3 = new TicketWindowThread("3号柜台");

		t1.start();
		t2.start();
		t3.start();
	}
}
