package org.example.juc_YangGe;


import java.util.concurrent.*;

class MyTask extends RecursiveTask<Integer>{
    private static final Integer ADJUST_VALUE = 100;
    private int begin;
    private int end;
    private int result;
    @Override
    protected Integer compute() {
        if ((end - begin) <= ADJUST_VALUE){
            for (int i = begin; i <= end ; i++) {
                result += i;
            }
        }else {
            int middle = (begin + end)/2;
            MyTask task01 = new MyTask(begin,middle);
            MyTask task02 = new MyTask(middle+1,end);
            task01.fork();
            task02.fork();
            result = task01.join() + task02.join();
        }
        return result;
    }
    public int getBegin() {
        return begin;
    }
    public void setBegin(int begin) {
        this.begin = begin;
    }
    public int getEnd() {
        return end;
    }
    public void setEnd(int end) {
        this.end = end;
    }
    public int getResult() {
        return result;
    }
    public void setResult(int result) {
        this.result = result;
    }
    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }
}

/**
 * 2020-07-08 20:44:59
 * 分支合并框架
 *
 * ForkJoinPool
 * ForkJoinTask
 * RecursiveTask extends ForkJoinTask
 *
 */
public class T_14_ForkJoin {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask(0,1000);
        ForkJoinPool threadPool = new ForkJoinPool();

        ForkJoinTask<Integer> forkJoinTask = threadPool.submit(myTask);
        System.out.println(forkJoinTask.get());

        threadPool.shutdown();
    }
}
