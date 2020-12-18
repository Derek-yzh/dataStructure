package org.example.juc.c_025;

import java.util.concurrent.LinkedTransferQueue;

/**
 * LinkedTransferQueue 与SynchronousQueue的区别有长度
 */
public class T09_TransferQueue {
	public static void main(String[] args) throws InterruptedException {
		LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();
		
		new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		
		strs.transfer("aaa");
		//strs.transfer("aaa");

		strs.put("aaa");
		System.out.println(strs.size());


		/*new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();*/


	}
}
