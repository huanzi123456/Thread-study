package com.huanzi.concurrency_basic.chapter3_stackSize;

/**
 * stackSize
 *		main线程(jvm创建)的stackSize 不受控制
 *		创建线程的时候，不指定的话默认为0;会忽略该参数，最终会被JNI函数调用，有些平台该参数无效
 *
 */
public class CreateThread3 {

	//局部变量的引用放到方法区，对象放到堆
	public static int counter = 0;
	//main线程由jvm创建
	public static void main(String[] args) {
		//虚拟机栈
		Thread t1 = new Thread(){
			@Override
			public void run() {
				try{
					add(0);
				}catch (Error e){
					// StackOverflowError --> 递归栈溢出
					e.printStackTrace();
					// output: 20662
					System.out.println(counter);
				}
			}
			private void add(int i) {
				++counter;
				add(i+1);
			}
		};
		t1.start(); //栈帧

		//测试栈深度
		Thread t2 = new Thread(null, new Runnable() {
			@Override
			public void run() {
				try{
					add(0);
				}catch (Error e){
					// StackOverflowError
					e.printStackTrace();
					// output: 1026837
					System.out.println(counter);
				}
			}
			private void add(int i) {
				++counter;
				add(i+1);
			}
		}, "test", 1 << 24); //二进制右移 8m

		t2.start();
	}
}
