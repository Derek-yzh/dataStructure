package org.example.juc_YangGe;

import java.util.concurrent.TimeUnit;

class Phone{

    public static synchronized void sendEMail() throws Exception{
        TimeUnit.SECONDS.sleep(4);//Thread.sleep(4000);
        System.out.println("*********sendEmail");
    }

    public synchronized void senSMS() throws Exception{
        System.out.println("*********sendSMS");
    }

    public void sayHello() throws Exception{
        System.out.println("********sayHello");
    }

}

/**
 *@create 2020-07-03 18:47:04
 *  8 lock
 *  1.标准访问，请问先打印邮件还是短信
 *  2.暂停4秒钟在邮件方法，请问先打印邮件还是短信
 *      一个对象里面如果有多个synchronized方法，某一个时刻内， 只要一个线程去 调用其中的一个synchronized方法了,
 *   它的线程都只能等待，换句话说，某--个时刻内，只能有唯一个线程去访问这些synchronized方法
 *   锁的是当前对象this,被锁定后，其它的线程都不能进入到当前对象的其它的synchronized方法
 *
 *  3.新增sayHello方法，请问先打印邮件还是hello
 *   加个普通方法后发现和同步锁无关
 *
 *  4.两部手机，请问先打印邮件还是短信
 *   换成两个对象后，不是同一把锁了，情况立刻变化。
 *
 *  5.两个静态同步方法，同一部手机，请问先打印邮件还是短信
 *  6.两个静态同步方法，两部手机，请问先打印邮件还是短信
 *     synchronized 对象锁 （加static 全局锁）
 *     synchronized实现同步的基础: Java 中的每一个对象 都可以作为锁。
 *     具体表现为以下3种形式。
 *         对于普通同步方法，锁是当前实例对象。
 *         对于同步方法块，锁是Synchronized括号里配置的对象。
 *         对于静态同步方法，锁是当前类的Class对象。
 *  7.一个静态同步方法，一个普通同步方法，一部手机
 *  8.一个静态同步方法，一个普通同步方法，两部手机
 *    一个锁Class 一个锁this
 *
 *
 */
public class T_03_Lock8 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            try {
                phone.sendEMail();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
            }
        },"A").start();
        Thread.sleep(100);
        new Thread(() -> {
            try {
                //phone.senSMS();
                //phone.sayHello();
                phone.senSMS();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
            }
        },"B").start();
    }

}
