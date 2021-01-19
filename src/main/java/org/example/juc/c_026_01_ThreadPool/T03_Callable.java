package org.example.juc.c_026_01_ThreadPool;

import org.example.juc.c_026_01_ThreadPool.mytask.MyFutureTask;
import org.example.juc.c_026_01_ThreadPool.mytask.MyThreadPoolExecutor;

import java.util.concurrent.*;

public class T03_Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> c = new Callable() {
            @Override
            public String call() throws Exception {
                System.out.println("Callable is going ...");
                return "Hello Callable";
            }
        };

        ExecutorService service = Executors.newCachedThreadPool();

        Future<String> future = service.submit(c); // 异步
        System.out.println(future.get());

        service.shutdown();

        System.out.println("=========自己实现的=========");
        MyThreadPoolExecutor my = new MyThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        Future<String> submit = my.submit(c);
        System.out.println(submit.get());

        my.shutdown();


    }

}
