package org.example.juc.c_017_MoreAboutSync;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * new 出来的不相等
 */
public class DoNotLockString {
	
	String s1 = new String("aaa");
	String s2 = new String("aaa");

	void m1() {
		synchronized(s1) {
 			try { TimeUnit.MILLISECONDS.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("m1");
 		}
	}
	
	void m2() {
		synchronized(s2) {
			System.out.println("m2");
		}
	}

	void test(){
		System.out.println(s1 == s2);
	}

	public static void main(String[] args) throws IOException {
		DoNotLockString d1 = new DoNotLockString();
		new Thread(d1::m1,"A").start();
		new Thread(d1::m2,"A").start();

		System.in.read();
		d1.test();
	}

}
