package org.example.lock;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Derek
 * @DateTime: 2021/1/15 22:21
 * @Description: TODO
 */
public class TestLock {

    @Test
    public void testLock() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ReentrantLock lock = new ReentrantLock(true);
        int[] count = {0};

        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName());
                    count[0]++;
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println(count[0]);


    }


}
