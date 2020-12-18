package org.example.juc.c_030_final;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Derek
 * @DateTime: 2020/12/12 19:10
 * @Description: 对象的内存布局
 */
public class HelloJOL {

    public static void main(String[] args) {

        try { TimeUnit.MILLISECONDS.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}

        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

}
