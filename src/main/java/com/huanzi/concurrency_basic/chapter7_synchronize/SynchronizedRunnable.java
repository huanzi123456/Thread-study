package com.huanzi.concurrency_basic.chapter7_synchronize;

public class SynchronizedRunnable implements Runnable {
	private int index = 1;
	private final static int MAX = 500;

	public void run() {
		while (true){
				/*if(index > MAX)
					break;
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread() + "的号码是" + (index++));*/

				if(ticket()){
					break;
				}

		}
	}
	//synchronized 加到方法上， 只有一个线程 可以拿到这个方法
	// 同步代码块，多个线程可以同时争抢，当前对象锁
	private  boolean ticket(){
		//this 当前类的实例
		synchronized (this){
			// 读取index
			if(index > MAX)
				return true;
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// index++ -> index = index + 1
			// 1. 读取index
			// 2. index = index + 1
			// 3. 将新index返回
			System.out.println(Thread.currentThread() + "的号码是" + (index++));
		}
		return false;
	}

}
