package com.huanzi.design_pattern.chapter4_OBSERVER_lifecycleListener.multithread_Observer_to_moniror_the_Thread_lifecycle;

/**
 * 被监控的线程对象：主题(事件源)
 */
public abstract class ObservableRunnable implements Runnable{

	//protetct 观察者
	private ObserverLifeCycleListener listener;

	// 注册观察者 ()
	public ObservableRunnable(ObserverLifeCycleListener listener) {
		this.listener = listener;
	}

	// 使用观察者的onEvent()方法来进行通知

	/**
	 * 事件源通知观察者
	 * @param event  状态量
	 */
	public void notifyChange(RunnableEvent event){
		listener.onEvent(event);
	}

	/**
	 * 线程状态枚举
	 */
	public enum RunnableStatus{
		RUNNING,ERROR,DONE
	}

	/**
	 * 线程事件(内部类)
	 * 包括线程的状态、当前线程、线程执行时发生的错误
	 */
	public static class RunnableEvent{
		private final RunnableStatus status;
		private final Thread thread;
		private final Throwable cause;

		public RunnableEvent(RunnableStatus status, Thread thread, Throwable cause) {
			this.status = status;
			this.thread = thread;
			this.cause = cause;
		}
		public RunnableStatus getStatus() {
			return status;
		}
		public Thread getThread() {
			return thread;
		}
		public Throwable getCause() {
			return cause;
		}
	}

}
