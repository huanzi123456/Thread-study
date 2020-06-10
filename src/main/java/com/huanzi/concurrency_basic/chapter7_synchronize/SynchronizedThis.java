package com.huanzi.concurrency_basic.chapter7_synchronize;

public class SynchronizedThis {

	public static void main(String[] args) {
		ThisLock lock = new ThisLock();
		/**
		 *如果m1  m2都是同步方法，则会挣抢锁（非同步执行）
		 *  t1先锁住了 this
		 *如果一个锁this，一个锁Object
		 *  则： 会同步执行
		 */
		new Thread(lock::m1,"T1").start();
		new Thread(lock::m2,"T2").start();
	}
}

class ThisLock{

	public synchronized void m1(){
		try {
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(10_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private final Object LOCK = new Object();

	public  void m2(){
		synchronized (LOCK){
			try {
				System.out.println(Thread.currentThread().getName());
				Thread.sleep(10_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
