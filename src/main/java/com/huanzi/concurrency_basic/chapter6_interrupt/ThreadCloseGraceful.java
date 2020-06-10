package com.huanzi.concurrency_basic.chapter6_interrupt;

public class ThreadCloseGraceful {

	public static void main(String[] args) {
		Worker w = new Worker(); w.start();
		try { Thread.sleep(1000);} catch (InterruptedException e) { e.printStackTrace(); }
		w.shutdown();
	}
	//worker类，多线程处理
	private static class Worker extends Thread{
		//volatile为 保证可见性
		private volatile boolean start = true;
		@Override
		public void run() { while(start){/*do something*/}}
		public void shutdown(){
			this.start = false;
		}
	}
}
