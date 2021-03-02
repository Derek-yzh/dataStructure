package org.example.juc.c_001;

/**
 * @author Derek
 * @DateTime: 2020/12/7 13:57
 * @Description: synchronized 关键字
 */
public class T {
	
	private int count = 10;
	private final Object o = new Object();
	
	public void m() {
		synchronized(o) {
			count--;
			System.out.println(Thread.currentThread().getName() + " count = " + count);
		}
	}
	
}

