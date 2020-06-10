package com.huanzi.concurrency_basic.chapter9_wait_notify.ProduceConsumerVersion2;

/**
 * 这是一个简单的生产者-消费者模型，但
 * 在多个生产者-多个消费者情况下会出现问题
 *		notify()是随机 唤醒的一个线程
 *		程序假死	如果c1 -> c2   全部wait()
 */
public class ProduceConsumer2 {
	private int i = 1;
	private final Object LOCK = new Object();
	private volatile boolean isProduced = false;

	private void produce() {
		synchronized (LOCK) {
			if (isProduced) {
				try {
					LOCK.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				// 这里表示i被消费，需要被生产，生产后通知消费者消费
				//生产
				i++;
				System.out.println("P->" + i);
				isProduced = true;
				LOCK.notify();
			}
		}
	}

	private void consumer(){
		synchronized (LOCK){
			if(isProduced){
				//消费
				System.out.println("C->"+i);
				isProduced = false;
				LOCK.notify();
			}else {
				// 这里表示i已经消费过了，通知生产者生产
				try {
					LOCK.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		ProduceConsumer2 p = new ProduceConsumer2();

		new Thread("P"){
			@Override
			public void run() {
				while (true){
					p.produce();
				}
			}
		}.start();

		new Thread("C"){
			@Override
			public void run() {
				while (true){
					p.consumer();
				}
			}
		}.start();
	}

}
