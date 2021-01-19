package org.example.juc.c_026_01_ThreadPool.mytask;

import java.util.concurrent.*;

/**
 * @Author: Derek
 * @DateTime: 2021/1/6 15:36
 * @Description: TODO
 */
public class MyThreadPoolExecutor extends ThreadPoolExecutor {


    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        if (task == null) throw new NullPointerException();
        //RunnableFuture<T> ftask = newTaskFor(task);
        MyFutureTask ftask = new MyFutureTask(task);
        execute(ftask);
        return ftask;
    }
}
