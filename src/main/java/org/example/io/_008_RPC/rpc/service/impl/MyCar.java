package org.example.io._008_RPC.rpc.service.impl;

import org.example.io._008_RPC.rpc.service.Car;

/**
 * @Author: Derek
 * @DateTime: 2020/12/18 18:42
 * @Description: TODO
 */
public class MyCar implements Car {
    @Override
    public String ooxx(String msg) {
        System.out.println("Car::ooxx 方法执行了...");
        return Thread.currentThread().getName()+"---server res---"+msg;
    }
}
