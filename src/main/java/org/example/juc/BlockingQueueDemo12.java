package org.example.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 2020-07-07 18:40:48
 * 阻塞队列
 *      方法类型    抛出异常    特殊值            阻塞      超时
 *        插入      add(e)     offer(e)false    put(e)    offer(e,time,unit)
 *        移除      remove()   poll()null       take()    poll(time,unit)
 *        检查      element()  peek()null       不可用     不可用
 */
public class BlockingQueueDemo12 {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));//入队
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //System.out.println(blockingQueue.add("x"));//抛出异常
        System.out.println(blockingQueue.remove());//出队
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove());//抛出异常

        System.out.println(blockingQueue.peek());//null
        System.out.println(blockingQueue.offer("a"));//入队
        System.out.println(blockingQueue.element()+"=======");//查看队首元素==peek()
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("x")+"=======");//不抛出异常

        System.out.println(blockingQueue.poll());//出队
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll()+"-------");//不抛出异常

        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        //blockingQueue.put("x");//入队操作，如果队列为满则阻塞等待

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take()+"===take===");
        //System.out.println(blockingQueue.take()+"===take===");//出队,如果队列为空则阻塞等待

        blockingQueue.offer("b");
        blockingQueue.offer("b");
        blockingQueue.offer("b");
        //入队操作，如果队满等待3秒
        System.out.println(blockingQueue.offer("a", 3L, TimeUnit.SECONDS));

    }
}
