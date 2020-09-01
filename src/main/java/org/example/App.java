package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 2020-07-02 09:00:38
 * maven自动生成类
 *
 */
public class App {

    /**
     * 输出当前时间
     */
    public static void nowTime() {
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(day));
    }

    public static void main(String[] args) {
        App.nowTime();
    }
}
