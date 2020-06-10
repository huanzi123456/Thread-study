package com.huanzi.design_pattern.chapter3_volatile;

/** todo 可见性:高速缓存一致性协议    有序性:内存屏障,
 *  * 	问题:两个线程 一个读数据
 *  * 				 一个存数据
 *  多线程:3个重要概念
 *  	原子性:对基本数据类型的变量读取是保证了原子性的,要么都成功,要么都失败,
 *  		a=10;原子性
 *  		b=a; 不是原子性的
 *
 * volatile：保证内存  可见性和有序性,不保证原子性
 * 		可见性:
 * 			强制对缓存的修改操作理解写入主存,
 * 			如果是写操作,会导致其他cpu中的缓存失效
 *
 *		有序性:
 *			happers-before
 *					代码的执行顺序,编写在前面的发生在编写在后面的
 *					unlock必须发生在lock后
 *					volatile修饰的变量,对一个变量的写操作,先于对该变量的读操作
 *					传递规则, a>b b>c  -->a>c
 *					线程的启动规则:start()先于线程run()
 *					线程中断规则:interrupt(),必须发生在捕获该动作之前
 *					对象销毁规则:初始化必须发生在finalize之前
 *					线程的终结规则: 所有的操作必须发生在线程死亡之前
 *
 *			代码有序执行,但是编译器可能会对 jvm指令进行优化,导致顺序不同
 *			但是编译器重排序要求达到最终一直性就可以!
 *				但是重排序在多线程里面会造成很大额影响!
 *
 *			屏障:多线程 --> double check 可能会空指针!
 *
 *	jmm:
 * 		缓存不一致问题:
 * 				jmm: 到内存cache去拿
 *				如果不保证INIT_VALUE可见性, READER就是一直从 缓存拿取数据!
 *				volatile
 *			解决办法: 总线:数据总线,控制总线,地址总线
 *				数据总线加锁!   cpu效率低下,串行化
 *			    缓存一致性协议: MESI
 *			    	cpu写入数据的时候,发现该变量被线程共享,通知其他cpu该变量的缓存无效!
 *					其他cpu,获取到主存获取
 * */
public class VolatileTest {

	private volatile static int INIT_VALUE = 0;

	private final static int MAX_LIMIT = 5;

	public static void main(String[] args) {
		new Thread(()->{
			int localValue = INIT_VALUE;
			while (localValue < MAX_LIMIT){
				if(localValue != INIT_VALUE){
					System.out.printf("The value is [%d]\n",INIT_VALUE);
					localValue = INIT_VALUE;
				}
			}
		},"READER").start();

		new Thread(()->{
			int localValue = INIT_VALUE;
			while (INIT_VALUE < MAX_LIMIT){
				if(localValue == INIT_VALUE){
					System.out.printf("Update the value to [%d]\n",++localValue);
					INIT_VALUE = localValue;
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"WRITER").start();
	}
}
