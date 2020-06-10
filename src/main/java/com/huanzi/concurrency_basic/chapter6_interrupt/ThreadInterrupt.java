package com.huanzi.concurrency_basic.chapter6_interrupt;

public class ThreadInterrupt {
	private static final Object MONITOR = new Object();

	public static void main(String[] args) {

		Thread t = new Thread(() -> { while (true){ } });
		t.start();
		//main线程打断
		Thread main = Thread.currentThread();

		Thread t2 = new Thread(() -> {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			main.interrupt();    //打断join()
//			t.interrupt();  todo t没有打断？
			System.out.println("interrupt");
		});
		t2.start();
		try {
			//t.join()   main等待t完成，  main直接被打断了
			t.join();   //join能不能抓取异常
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//抓取异常，程序break中断
	private static void testWait() {
		Thread t1 = new Thread(()->{
			while (true){
				synchronized (MONITOR){
					try {
						MONITOR.wait(10);
					} catch (InterruptedException e) {
						//拿到当前线程的 currentThread().isInterrupted(true);
						System.out.println("while"+Thread.interrupted());
						break;
					}
				}
			}
		});
		t1.start();
		try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
		System.out.println(t1.isInterrupted());
		t1.interrupt();
		System.out.println(t1.isInterrupted());
	}

	//打断，程序异常，程序结束 break;
	private static void testInterrupt() {
		Thread t = new Thread(() -> {
			while (true) {
				synchronized (MONITOR) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						System.out.println("Interrupt...");
						break;
					} } } });
		t.start();
		try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
		System.out.println(t.isInterrupted());
		t.interrupt();
		System.out.println(t.isInterrupted());
	}
}

