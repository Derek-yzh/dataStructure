package org.example.juc_YangGe.more;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author: Derek
 * @DateTime: 2020/9/4 11:15
 * @Description: ABA_Solution : AtomicStampedReference
 */
public class T_ABA_Solution {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    static AtomicStampedReference<Integer> reference = new AtomicStampedReference<>( 100,1);

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            System.out.println("----start---");
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
            System.out.println("----stop---");
        },"A").start();

        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(
                    atomicReference.compareAndSet(100, 200)+"\t"+atomicReference.get());

        }, "A").start();

        try {TimeUnit.MILLISECONDS.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}

        System.out.println("===================加版本号后==================");

        new Thread(() -> {

            System.out.println(Thread.currentThread().getName()+"\t第一次版本号:"+reference.getStamp());
            try {TimeUnit.MILLISECONDS.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
            reference.compareAndSet(100,101,
                    reference.getStamp(),reference.getStamp()+1);

            System.out.println(Thread.currentThread().getName()+"\t第二次版本号:"+reference.getStamp());
            try {TimeUnit.MILLISECONDS.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
            reference.compareAndSet(101,100,
                    reference.getStamp(),reference.getStamp()+1);

            },"aa").start();


        new Thread(() -> {

            int stamp = reference.getStamp();//版本号
            System.out.println(Thread.currentThread().getName()+"\t第一次版本号:"+stamp);
            try {TimeUnit.MILLISECONDS.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
            boolean result = reference.compareAndSet(100, 2019, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName()+"\t修改成功否:"+result+"\t当前实际版本号:"+reference.getStamp());

            },"bb").start();


    }

}
