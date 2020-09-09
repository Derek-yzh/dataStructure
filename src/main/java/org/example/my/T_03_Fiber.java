package org.example.my;

/**
 * @Author: Derek
 * @DateTime: 2020/9/5 14:23
 * @Description:
 *  Test Fiber
 *  Java中对于纤程的支持，没有内置，盼望内置
 */
public class T_03_Fiber {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                calc();
            }
        };

        for (int i = 0; i < 10000; i++) {

            /*Fiber<Void> fiber = new Fiber<>(new SuspendableRunnable() {
                @Override
                public void run() throws SuspendExecution, InterruptedException {
                    calc();
                }
            });

            fiber.start();*/

            /*Thread thread = new Thread(r);
            thread.start();*/
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void calc() {
        int result = 0;
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 200; j++) {
                result += i;
            }
        }
    }

}
