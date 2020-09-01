package org.example.juc;

/**
 * 2020-07-03 09:03:40
 * 线程可见性
 */
public class TestVolatile {
    private static volatile /*线程可见性*/boolean flag = true;
    {
        System.out.println("sddddd");//构造块
    }
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (flag){
            }
            System.out.println("end");
        },"A").start();

        Thread.sleep(1000);
        new Thread(() -> {
            flag = false;
        },"A").start();

        String a = "123";
        String b = "123";
        System.out.println(a == b);
    }
}
