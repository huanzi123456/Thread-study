package com.huanzi.concurrency_basic.chapter9_wait_notify.ProduceConsumerVersion1;

/**
 * 线程没有通讯
 */
public class ProduceConsumer1 {
	private int i = 1;
	private final Object LOCK = new Object();
	//生产数据
	private void produce(){
		synchronized (LOCK){
			System.out.println("P->"+(i++));
		}
	}
	//消费数据
	private void consumer(){
		synchronized (LOCK){
			System.out.println("C->"+(i++));
		}
	}

	//两个线程，线程没有通讯，生产消费是错误的
	public static void main(String[] args) {
		ProduceConsumer1 p = new ProduceConsumer1();
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
