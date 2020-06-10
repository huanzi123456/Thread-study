package com.huanzi.design_pattern.chapter11_Context上下文;

import java.util.Random;

public class QueryFromDBAction {
	private static Random random = new Random(System.currentTimeMillis());

	//传参数问题,大小不定
	public void execute(){
		try {
			Thread.sleep(random.nextInt(1000));
			String name = "leo " + Thread.currentThread().getName();

			//todo 多线程情况下,获取的时候先清理该Context  同一个内容决定于key
			Context context = ActionContext.getActionContext().getContext();
			context.setName(name);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
