package org.example.juc.c_025;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class T06_ArrayBlockingQueue {

	static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

	static Random r = new Random();

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			strs.put("a" + i);
		}
		
		//strs.put("aaa"); //满了会阻塞
		//strs.add("aaa"); //满了会报异常
		//strs.offer("aaa"); //满了会返回false
		strs.offer("aaa", 5, TimeUnit.SECONDS);//满了会阻塞5s
		
		System.out.println(strs);
	}
}
