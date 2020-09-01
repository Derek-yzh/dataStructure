package org.example.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 2020-07-08 21:33:11
 */
public class CompletableFutureDemo14 {
    public static void main(String[] args) throws Exception{
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "：没有返回值,update mysql");
        });
        System.out.println(completableFuture.get()+"=========");
        //Thread.sleep(1000);
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "：有返回值,select mysql");
            //int age = 1/0;
            return 1024;
        });
        System.out.println("异步已经去执行了！");
        //System.out.println("异步的结果为："+integerCompletableFuture.get());

        System.out.println(integerCompletableFuture.whenComplete((t, u) -> {
            if (t != null) {
            System.out.println("****结果t:" + t);
            System.out.println("****异常u:" + u);
            }
        }).exceptionally(f -> {
            System.out.println("***exception:" + f.getMessage());
            return 4444;
        }).get());

    }
}
