package com.huanzi.design_pattern.chapter12_BALKING;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * 对应一个文件
 */
public class BalkingData {
	private String fileName;
	private String content;
	private boolean changed;

	public BalkingData(String fileName, String content) {
		this.fileName = fileName;
		this.content = content;
		this.changed = true;
	}
	//已经改变
	public void change(String content){
		this.content = content;
		this.changed = true;
	}

	public void save(){
		if(!changed){
			return;
		}
		doSave();
	}

	private void doSave() {
		//fileWriter();
		try (PrintWriter printWriter = new PrintWriter(fileName)) {
			printWriter.print(content);
			printWriter.flush();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("The file has saved.");
	}
}
