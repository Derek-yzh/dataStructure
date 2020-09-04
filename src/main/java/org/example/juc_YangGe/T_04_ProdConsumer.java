package org.example.juc_YangGe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Pcd1{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void increment()throws Exception{
        lock.lock();
        try {
            while (number != 0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"生产:\t"+number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void decrement()throws Exception{
        lock.lock();
        try {
            while (number == 0){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"消费:\t"+number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    /*public synchronized void increment()throws Exception{
        //1.判断
        while (number != 0){
            this.wait();
        }
        //2.干活
        number++;
        //3.通知
        System.out.println(Thread.currentThread().getName()+"生产:\t"+number);
        this.notifyAll();
    }
    public synchronized void decrement()throws Exception{
        while (number == 0){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"消费:\t"+number);
        this.notifyAll();
    }*/
}

/**
 * 2020-07-04 15:12:35
 * 题目:现在两个线程，可以操作初始值为零的一个变量,
 * 实现一个线程对该变量加1，一个线程对该变量减1 ,
 * 实现交替，来10轮，变量初始值为零。
 *
 * 1 高聚低合前提下，线程操作资源类
 * 2 判断/干活/通知
 * 3 防止虚假唤醒 判断用while不用if 多线程横向判断用while (因为wait被唤醒后只有while会重新判断,if不会)
 *
 *
 */
public class T_04_ProdConsumer {

    public static void main(String[] args) {
        Pcd1 pcd = new Pcd1();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    pcd.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A1").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    pcd.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B1").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    pcd.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A2").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    pcd.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B2").start();
    }

}
