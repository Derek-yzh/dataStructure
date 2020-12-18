package org.example.io._008_RPC.rpc.service.impl;

import org.example.io._008_RPC.rpc.service.Fly;

/**
 * @Author: Derek
 * @DateTime: 2020/12/18 18:42
 * @Description: TODO
 */
public class MyFly implements Fly {
    @Override
    public void ooxx(String msg) {
        System.out.println("Fly::ooxx...");
    }
}
