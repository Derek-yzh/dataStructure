package org.example.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @create 2020-07-03 17:12:26
 *
 * 1.故障现象
 *  java.util.ConcurrentModificationException:并发修改异常
 * 2.导致原因
 *
 * 3.解决办法
 *  3.1 new Vector<>();
 *  3.2 Collections.synchronizedList(new ArrayList<>());//Collections:集合接口的工具类
 *  3.3 new CopyOnWriteArrayList<>();
 *
 * 4.优化建议（同样的错误不犯第二次）
 *
 *  关于HashMap
 *    初始值16 负载因子0.75 扩容会到原来的一倍
 *
 *
 *
 */
public class NotSafeDemo01 {

    public static void listNotSafe() {
        //默认容量为10第一次扩容(通过右移一位)为15第二次22  vector默认扩容为原来的2倍 hashMap默认容量为16扩容为原值的一倍
        //new Vector<>();//new ArrayList<>()
        //Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();//写时复制技术(读写分离思想的一种体现)

        for (int i = 1; i <= 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(Thread.currentThread().getName()+":"+list);
            },String.valueOf(i)).start();
        }
    }

    public static void setNotSafe() {
        Set<Object> set = new CopyOnWriteArraySet<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(Thread.currentThread().getName()+":"+set);
            },String.valueOf(i)).start();
        }
    }

    public static void main(String[] args) {
        Map<String,String> map = new ConcurrentHashMap<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 8));
                System.out.println(Thread.currentThread().getName()+":"+map);
            },String.valueOf(i)).start();
        }
    }

}
