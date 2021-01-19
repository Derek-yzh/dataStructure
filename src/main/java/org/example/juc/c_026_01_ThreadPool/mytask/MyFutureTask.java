package org.example.juc.c_026_01_ThreadPool.mytask;

import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: Derek
 * @DateTime: 2021/1/6 15:14
 * @Description: TODO
 */
public class MyFutureTask extends FutureTask {

    public MyFutureTask(Callable callable) {
        super(callable);
    }

    @Override
    public void run() {
        super.run();
        System.out.println("This is my futureTask !");
    }
}

