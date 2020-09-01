package org.example.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 2020-07-07 15:30:00
 * 多线程工具类
 * 做加法
 * 人到齐才开会 集齐七颗龙珠召唤神龙
 */
public class CyclicBarrieDemo08 {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("******召唤神龙");
        });

        for (int i = 1; i <= 7; i++) {
            final int tempINt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t收集到第"+tempINt+"颗龙珠");
                try {
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName()+"退出");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },""+i).start();
        }

    }
}
