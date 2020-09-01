package org.example.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 2020-07-06 20:01:50
 * 多线程工具类
 * 关于countDownLatch
 * 教室有六个人，都走完后锁门
 * countDownLatch.countDown():将计数器减一
 * 倒计时
 */
public class CountDownLatchDemo09 {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 6; i++) {
            int finali = i;
            new Thread(()->{
                try { TimeUnit.MILLISECONDS.sleep(300* finali);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+"离开了!");
                countDownLatch.countDown();
            },"同学"+(i+1)).start();
        }
        countDownLatch.await();
        System.out.println("班长锁门！");
    }


}
