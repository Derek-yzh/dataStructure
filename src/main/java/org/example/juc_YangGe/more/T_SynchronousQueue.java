package org.example.juc_YangGe.more;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Derek
 * @DateTime: 2020/9/4 16:41
 * @Description: Test SynchronousQueue :
 *                  Don't store elements;
 *                  Take one after putting one;
 *                  If nothing taking block;
 */
public class T_SynchronousQueue {

    public static void main(String[] args) {

        SynchronousQueue<String> queue = new SynchronousQueue<>();

        new Thread(() -> {
            while (true) {

                try {TimeUnit.MILLISECONDS.sleep(1000);} catch (InterruptedException e) {}
                try {
                    queue.put("aaa");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(() -> {
            while (true){
                try {
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

    }
}
