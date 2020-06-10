package com.huanzi.design_pattern.chapter9_guarded_suspention;

import java.util.Random;

public class RequestClient extends Thread{
	private final RequestQueue queue;
	private final String value;
	private final Random random;

	public RequestClient(RequestQueue queue, String value) {
		this.queue = queue;
		this.value = value;
		this.random = new Random();
	}

	@Override
	public void run() {
		int i=0;
		while (i<20){
			System.out.println("ClientAndService -> " + value);
			queue.pushRequest(new Request(value));
			try {
				Thread.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		}
	}
}
