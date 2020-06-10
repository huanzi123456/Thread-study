package com.huanzi.concurrency_basic.chapter7_synchronize;

public class TicketWindowRunnable implements Runnable {
	private  int index = 1;
	private final static int MAX = 500;

	private final Object MONITOR = new Object();

	public void run() {
		while (true){
			//todo 不加锁? 资源的争抢，会导致出现问题！线程切换导致
			//当一个线程T1拿到499，可能另外两个线程也已经拿到了499，并且已经经过了验证，两次执行了++ 501
			//T1拿到的是 499，验证完成，再去+，就成了502了！

//			synchronized (MONITOR){
				if(index > MAX)
					break;
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread() + "的号码是" + (index++));
			}
//		}
	}
}
