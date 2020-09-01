package org.example.juc;

import java.util.concurrent.Semaphore;

/**
 * 2020-07-07 15:43:36
 * 多线程工具类
 * 多抢多 在信号量semaphore上有两种操作  acquire:获取 release:释放
 * 信号量主要用于两个目的：
 *      1.多个共享资源互斥使用
 *      2.并发线程数的控制
 * 例如：抢车位
 */
public class SemaphoreDemo10 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟资源类有，有三个空车位
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t抢占到了车位！");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName()+"\t离开了车位！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
