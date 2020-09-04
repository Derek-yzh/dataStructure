package org.example.juc_YangGe;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Derek
 * @DateTime: 2020/9/4 19:15
 * @Description: test DeanLock
 */
public class T_16_DeadLock {
    public static void main(String[] args) {
        String lookA = "lookA";
        String lookB = "lookB";
        new Thread(new Demo("lookA",lookB),"A").start();
        new Thread(new Demo(lookB,lookA),"B").start();
    }
}

class Demo implements Runnable{

    private String lockA;
    private String lockB;

    public Demo(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t自己持有"+lockA+"\t尝试获得"+lockB);
            try { TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}

            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t自己持有"+lockB+"\t尝试获得"+lockA);
            }

        }
    }
}
