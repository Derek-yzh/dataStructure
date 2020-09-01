package org.example.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class CdThread implements Callable<Integer>{
    private int a;
    private int b;

    public CdThread(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("***********come in callable");
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName()+"结束了!");
        return a+b;
    }
}

/**
 * 2020-07-04 17:37:58
 * 关于Callable接口的使用
 */
public class CallableDemo06 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.nanoTime();

        FutureTask<Integer> futureTask = new FutureTask<>(new CdThread(3,3));
        Thread t1 = new Thread(futureTask, "A");
        t1.start();
        //t1.join();

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()+"结束了!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B");
        t2.start();
        t2.join();

        Thread t3 = new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()+"结束了!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C");
        t3.start();
        t3.join();

        Integer result = futureTask.get();
        long l1 = (System.nanoTime() - start) / 1000_000_000L;
        System.out.print("第一次计算用了"+l1+"s结果为:");
        System.out.println(result);

        Thread t11 = new Thread(futureTask, "A");
        t11.start();
        result = futureTask.get();
        long l2 = (System.nanoTime() - start) / 1000_000_000L;
        System.out.print("第二次计算用了"+(l2-l1)+"s结果为:");
        System.out.println(result);
    }
}
