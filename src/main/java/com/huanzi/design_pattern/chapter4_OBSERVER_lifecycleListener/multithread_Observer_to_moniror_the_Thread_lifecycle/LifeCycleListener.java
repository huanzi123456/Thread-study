package com.huanzi.design_pattern.chapter4_OBSERVER_lifecycleListener.multithread_Observer_to_moniror_the_Thread_lifecycle;

public interface LifeCycleListener {

	void onEvent(ObservableRunnable.RunnableEvent event);
}
