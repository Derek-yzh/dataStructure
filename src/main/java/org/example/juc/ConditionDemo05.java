package org.example.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Cd1{
    private int number = 1;// A:1 B:2 C:3
    private Lock lock = new ReentrantLock();
    //一把锁配多把🔑
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void printA(){
        lock.lock();
        try {
            //判断
            while (number != 1){
                conditionA.await();
            }
            //干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+":AA"+i);
            }
            //通知第二个
            number = 2;
            conditionB.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printB(){
        lock.lock();
        try {
            while (number != 2){
                conditionB.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+":BB"+i);
            }
            number = 3;
            conditionC.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printC(){
        lock.lock();
        try {
            while (number != 3){
                conditionC.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+":CC"+i);
            }
            number = 1;
            conditionA.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

/**
 * @create 2020-07-04 17:07:21
 * 关于：Lock
 * 多线程之间按顺序调用，实现A -> B -> C
 * 三个线程启动,要求如下：
 *
 * AA打印五次,BB打印10次,CC打印15次
 * 接着
 * AA打印五次,BB打印10次,CC打印15次
 * 来十轮
 *
 */
public class ConditionDemo05 {
    public static void main(String[] args) {
        Cd1 cd1 = new Cd1();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                cd1.printA();
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                cd1.printB();
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                cd1.printC();
            }
        },"C").start();
    }
}
