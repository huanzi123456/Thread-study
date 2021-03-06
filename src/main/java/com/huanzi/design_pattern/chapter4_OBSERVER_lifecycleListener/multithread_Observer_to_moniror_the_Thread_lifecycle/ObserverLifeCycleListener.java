package com.huanzi.design_pattern.chapter4_OBSERVER_lifecycleListener.multithread_Observer_to_moniror_the_Thread_lifecycle;

import java.util.List;

/**
 * 线程生命周期的监管者
 *
 */
public class ObserverLifeCycleListener implements LifeCycleListener {

	// 为每个线程注册监听者和安排任务
	//查询的任务
	public void concurrentQuery(List<String> ids) {
		if (ids == null || ids.isEmpty()) {
			return;
		}
		//创建线程 根据id的个数创建线程
		ids.forEach(id -> new Thread(new ObservableRunnable(this) {
			@Override
			public void run() {
				try {
					notifyChange(new RunnableEvent(RunnableStatus.RUNNING, Thread.currentThread(), null));
					System.out.println("Query for the id is " + id);
					Thread.sleep(1000);
//					int i = 1/0; error status
					notifyChange(new RunnableEvent(RunnableStatus.DONE, Thread.currentThread(), null));
				} catch (Exception e) {
					notifyChange(new RunnableEvent(RunnableStatus.ERROR, Thread.currentThread(), e));
				}
			}
		}, id).start());
	}

	//定义锁
	private final Object LOCK = new Object();

	// 监管者被通知后，可以通过“主题”传递的信息做一些操作
	//具体的观察者,  回调
	@Override
	public void onEvent(ObservableRunnable.RunnableEvent event) {
		//多线程访问 加锁保证数据安全
		synchronized (LOCK) {
			//打印状态
			System.out.println("The runnable [" + event.getThread().getName() + "] data changed and status is " + event.getStatus());
			if (event.getCause() != null) {
				System.out.println("The runnable [" + event.getThread().getName() + "] process failed.");
				event.getCause().printStackTrace();
			}
		}
	}
}
