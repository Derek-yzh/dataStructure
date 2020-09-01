package org.example.jvm.mashibing;

/**
 * 2020-07-06 08:09:33
 * 测试缓存行
 */
public class T04_CacheLinePadding {
    private static class Padding{
        public volatile long p1,p2,p3,p4,p5,p6,p7;
    }

    private static class T extends Padding{
        public volatile long x = 0L;
    }

    private static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (long i = 0; i < 1000_0000L; i++) {
                arr[0].x = i;
            }
        });
        Thread thread2 = new Thread(() -> {
            for (long i = 0; i < 1000_0000L; i++) {
                arr[1].x = i;
            }
        });

        final long start = System.nanoTime();
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println((System.nanoTime()-start)/100_0000);

    }
}
