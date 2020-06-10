package com.huanzi.design_pattern.chapter17_WORKER_THREAD;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 工厂流水线模式:传送带
 */
public class Channel {

	private LinkedList<Request> RequestQueue;

	private WorkerTread[] workerThreads;

	private int workerCount;

	private int queueMaxCount;

	/**
	 * 传送带 管理worker
	 * @param workerCount
	 */
	public Channel(int workerCount) {
		this.workerCount = workerCount;
		this.RequestQueue = new LinkedList<>();
		this.workerThreads = new WorkerTread[workerCount];
		this.queueMaxCount = 100;
		this.init();
	}

	private void init() {
		for (int i = 0; i < workerCount; i++) {
			workerThreads[i] = new WorkerTread("Worker Thread-" + i, this);
		}
	}

	public void startWork() {
		//consumer消费方式   程序式接口
		Arrays.asList(workerThreads).forEach(WorkerTread::start);
	}

	/**
	 * 放入货物
	 * @param request
	 */
	public synchronized void put(Request request){
		//
		while (RequestQueue.size() >= queueMaxCount){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.RequestQueue.addLast(request);
		this.notifyAll();
	}

	/**
	 * 拿取货物
	 * @return
	 */
	public synchronized Request take(){
		while (RequestQueue.isEmpty()){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Request request = RequestQueue.removeFirst();
		this.notifyAll();
		return request;
	}


}
