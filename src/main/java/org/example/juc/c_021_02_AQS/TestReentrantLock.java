package org.example.juc.c_021_02_AQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {

    private static volatile int i = 0;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        //synchronized (TestReentrantLock.class) {
            i++;
        //}

        try { TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(() -> {
            lock.lock();
            try {TimeUnit.MILLISECONDS.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
        },"A").start();

        /*new Thread(() -> {
            lock.lock();
            },"B").start();*/

        try {TimeUnit.MINUTES.sleep(30000000);} catch (InterruptedException e) {e.printStackTrace();}
        lock.unlock();

        //synchronized 程序员的丽春院 JUC
    }
}
