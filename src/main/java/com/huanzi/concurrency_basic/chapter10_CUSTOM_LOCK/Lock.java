package com.huanzi.concurrency_basic.chapter10_CUSTOM_LOCK;

import java.util.Collection;
//锁的通用方法
public interface Lock {

	class TimeoutException extends Exception{
		TimeoutException(String msg){
			super(msg);
		}
	}

	void lock();

	void lock(long mills) throws TimeoutException;

	void unlock();

	Collection<Thread> getBlockedCollection();

	int getCollectionOfBlockedThreadSize();
}
