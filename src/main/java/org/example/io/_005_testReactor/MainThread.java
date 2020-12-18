package org.example.io._005_testReactor;

/**
 * @Author: Derek
 * @DateTime: 2020/12/17 11:33
 * @Description: 这里不做IO和业务的事情
 */
public class MainThread {

    public static void main(String[] args) {

        //1.创建IO Thread (一个或者多个)
        SelectorTreadGroup stg = new SelectorTreadGroup(2,2);
        //混杂模式：只有一个线程负责accept，每个都会被分配client，进行R/W
        //SelectorTreadGroup stg = new SelectorTreadGroup(3);


        //2.把监听(9999)的server注册到某一个selector上
        stg.bind(9997);
        stg.bind(9998);
        stg.bind(9999);


    }

}
