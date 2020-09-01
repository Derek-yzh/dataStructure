package org.example.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{//资源类 = 实例变量 + 实例方法
    private int num = 30;
    Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            if (num > 0){
                System.out.println(Thread.currentThread().getName()+"\t卖出第："+(num--)+"\t还剩下："+num);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

/**
 * 函数式接口 才可以用lambda表达式
 */
@FunctionalInterface
interface Foo{
    public int add(int x,int y);
    public default int test(int x, int y){
        return x*y;
    };
    public static int mul(int x, int y){
        return x*y;
    };
}

/**
 * @create 2020-07-03 17:12:26
 * 题目:三个传票员 卖出  30张票
 * 笔记:如何编写企业级的多线程代码
 *
 * 固定的编程套路+模板是什么？
 * 1.在高内聚低耦合的前提下，线程     操作      资源类
 *      1.1 一言不合，先创建一个资源类
 *
 */
public class SaleTicketDemo102 {
    public static void main(String[] args) {//主线程，一切程序的人口
        /*Foo foo = Integer::sum;
        System.out.println(foo.test(2,2));
        Foo.mul(3,8);*/

        final Ticket ticket = new Ticket();
        new Thread(()-> { for (int i = 0; i <= 40; i++) ticket.sale(); }, "A").start();
        new Thread(()-> { for (int i = 0; i <= 40; i++) ticket.sale(); }, "B").start();
        new Thread(()-> { for (int i = 0; i <= 40; i++) ticket.sale(); }, "C").start();


    }
}
