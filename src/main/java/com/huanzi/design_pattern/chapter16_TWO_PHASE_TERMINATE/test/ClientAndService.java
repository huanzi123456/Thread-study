package com.huanzi.design_pattern.chapter16_TWO_PHASE_TERMINATE.test;

/**
 * 二阶段关闭
 */
public class ClientAndService {
	public static void main(String[] args) throws InterruptedException {
		AppServer server = new AppServer(12345);
		server.start();

		Thread.sleep(10_000);
		server.shutdown();
	}
}
