package com.huanzi.concurrency_basic.chapter11_hook_CaptureException;

/**
 * 异常捕捉:
 *
 *
 */
public class ThreadException {
	public static final int A = 10;
	public static final int B = 0;
	public static void main(String[] args) {
		testExceptionHandler();
	}

	private static void testExceptionHandler() {
		Thread t = new Thread(()->{
			try {
				Thread.sleep(5_000);
				int result = A / B;
				System.out.println(result);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
//		//获取栈帧信息
//		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
//		Arrays.asList(stackTrace).stream().filter(e->!e.isNativeMethod())
//				.forEach(e-> Optional.of(e.getClassName()+ ":" + e.getMethodName()+":"+e.getLineNumber())
//						.ifPresent(System.out::println));

		/**
		 * 可以通过接口方法，捕获到异常之后，
		 * 对异常和发生异常的线程做一些事情
		 */
		t.setUncaughtExceptionHandler((thread,e)->{
			System.out.println(e);
			//todo 线程组
			System.out.println(thread);
		});
		t.start();
	}
}
