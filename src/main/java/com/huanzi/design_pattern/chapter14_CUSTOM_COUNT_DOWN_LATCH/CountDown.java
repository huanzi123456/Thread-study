package com.huanzi.design_pattern.chapter14_CUSTOM_COUNT_DOWN_LATCH;

//countDownLatch()
public class CountDown {
	private int total;
	//计数器
	private int count;

	public CountDown(int total) {
		this.total = total;
		this.count = 0;
	}

	public void cut(){
		synchronized (this){
			total--;
			this.notifyAll();
		}
	}

	public void await() throws InterruptedException {
		synchronized (this){
			while (count != total){
				this.wait();
			}
		}
	}
}
