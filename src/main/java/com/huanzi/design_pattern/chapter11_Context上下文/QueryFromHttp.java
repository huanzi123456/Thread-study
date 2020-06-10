package com.huanzi.design_pattern.chapter11_Context上下文;

import java.util.Random;

public class QueryFromHttp {
	private static Random random = new Random(System.currentTimeMillis());
	public void execute(){
		try {
			Thread.sleep(random.nextInt(1000));
			Context context = ActionContext.getActionContext().getContext();
			String name = context.getName();
			String cardId = initValue(name);
			context.setCardId(cardId);
			context.setName(name);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private String initValue(String name) {
		return "2342345345 " + Thread.currentThread().getName();
	}
}
