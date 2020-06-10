package com.huanzi.concurrency_basic.chapter11_hook_CaptureException;

public class CaptureThreadRuntimeException {
	public static void main(String[] args) {
		/**
		 * 添加运行时钩子程序，当抛出运行时异常时钩子程序启动，
		 * 并执行一些钩子程序中定义的函数或实现，
		 * 钩子程序在抛出异常或程序被人为终止时(使用kill process)会被调用
		 * 	kill -9 pid    不会执行
		 */
		Runtime.getRuntime().addShutdownHook(new Thread(()->{
			System.out.println("will be shutdown");
			// other more method...
			notifyAndPrint();
		}));
		int i = 0;
		while (true){
			try {
				Thread.sleep(1_000);
				System.out.println("I am working...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if((i++)>3){
				throw new RuntimeException("error");
			}
		}
	}
	//通过钩子方法,去释放资源,通知
	private static void notifyAndPrint() {
		System.out.println("will release resources of socket, file and connector..");
	}
}
