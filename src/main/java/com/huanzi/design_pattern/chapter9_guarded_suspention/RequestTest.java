package com.huanzi.design_pattern.chapter9_guarded_suspention;

/**
 * 确保挂起:  要等到我准备好,工作线程很忙,
 * 		等闲下来再去做
 */
public class RequestTest {
	public static void main(String[] args) throws InterruptedException {
		RequestQueue queue = new RequestQueue();
		new RequestClient(queue, "leo").start();
		RequestServer server = new RequestServer(queue);
		server.start();

		//做完关掉
		Thread.sleep(10_000);
		server.close();
	}
}
