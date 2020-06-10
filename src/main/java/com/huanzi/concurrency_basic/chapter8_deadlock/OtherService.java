package com.huanzi.concurrency_basic.chapter8_deadlock;

public class OtherService {
	private DeadLock deadLock;

	private static final Object LOCK = new Object();

	public void o1() {
		synchronized (LOCK){
			System.out.println("o1===========");
			deadLock.d2();
		}
	}

	public void o2() {
		synchronized (LOCK){
			System.out.println("o1===========");
		}
	}

	public void setDeadLock(DeadLock deadLock) {
		this.deadLock = deadLock;
	}
}
