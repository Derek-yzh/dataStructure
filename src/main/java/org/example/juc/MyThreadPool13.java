package org.example.juc;

import java.util.concurrent.*;

/**
 * 2020-07-08 14:30:11
 * 线程池
 * 线程池玩儿的是ThreadPoolExecutor类
 *   参数：(7)
 *        int corePoolSize, 线程池中常驻核心数
 *        int maximumPoolSize, 线程池中能够容纳同时执行的最大线程数，此值必须大于等于1
 *        long keepAliveTime, 多余空闲线程的存活时间，当线程池数量中线程数量超过corePoolSize时,
 *            当空闲时间达到keepAliveTime时,多余线程会被销毁直到只剩下corePoolSize个线程为止
 *        TimeUnit unit,  keepAliveTime单位
 *        BlockingQueue<Runnable> workQueue, 任务队列，被提交但尚未被执行的任务
 *        ThreadFactory threadFactory,表示生成线程池中工作线程的工厂,用于创建线程,一般默认即可
 *        RejectedExecutionHandler handler，拒绝策略，表示当队列满了，并且工作线程大于等于
 *                                  线程池的最大线程数时如何来拒绝请求执行的runnable的策略
 *           java.util.concurrent.RejectedExecutionException
 *         拒绝策略：
 *           AbortPolicy(默认):直接抛出RejectedExecutionException异常阻止 系统正常运行
 *           CallerRunsPolicy:"调用者运行"一种调节机制，该策略既不会抛弃任务，也不
 *                            会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量。
 *           DiscardOldestPolicy:抛弃队列中等待最久的任务，然后把当前任务加人队列中尝试再次提交当前任务。
 *           DiscardPolicy:该策略默默地丢弃无法处理的任务,不予任何处理也不抛出异常。
 *                         如果允许任务丢失，这是最好的一种策略。
 * Executors.newFixedThreadPool(5) 一池五个线程
 *      执行长期任务性能好，创建一个线程池，
 *      一池有N个固定的线程，有固定线程数的线程
 * Executors.newSingleThreadExecutor() 一池一个工作线程
 * Executors.newCachedThreadPool()
 *      执行很多短期异步任务，线程池根据需要创建新线程,
 *      但在先前构建的线程可用时将重用它们。可扩容,遇强则强
 *
 * 1、在创建了线程池后，开始等待请求。
 * 2、当调用execute ()方法添加一个请求任务时，线程池会做出如下判断:
 *  2.1如果正在运行的线程数量小于corePoolSize，那么马上创建线程运行这个任务;
 *  2.2如果正在运行的线程数量大于或等于corePoolSize，那么将这个任务放入队列;
 *  2.3如果这个时候队列满了且正在运行的线程数量还小于maximumPoolSize,那么还是要创建非核心线程立刻运行这个任务;
 *  2.4如果队列满了且正在运行的线程数量大于或等于maximumPoolSize,那么线程池会启动饱和拒绝策略来执行。
 * 3、当一个线程完成任务时，它会从队列中取下一个任务来执行。
 * 4、当一个线程无事可做超过-定的时间(keepAliveTime) 时，线程会判断:
 *      如果当前运行的线程数大于corePoolSize，那么这个线程就被停掉。
 *      所以线程池的所有任务完成后，它最终会收缩到corePoolSize的大小。
 */
public class MyThreadPool13 {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                //cpu密集型机子一般为cpu核数(8)+1或者+2//I/O密集型一般为核数两倍
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
                );
        try {
            for (int i = 1; i <= 8; i++) {
                threadPool.execute(()->{
                    try {TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

    /**
     * 测试工具类
     * Executors不常用，用ThreadPoolExecutor类
     */
    private static void testExecutors() {
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池五个线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池一个工作线程
        ExecutorService threadPool = Executors.newCachedThreadPool();//一池n线程
        try {
            for (int i = 1; i <= 10; i++) {
                try {TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
