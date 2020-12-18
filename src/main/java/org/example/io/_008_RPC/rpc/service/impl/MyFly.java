package org.example.io._008_RPC.rpc.service.impl;

import org.example.io._008_RPC.rpc.service.Fly;
import org.example.io._008_RPC.rpc.service.Person;

/**
 * @Author: Derek
 * @DateTime: 2020/12/18 18:42
 * @Description: TODO
 */
public class MyFly implements Fly {

    @Override
    public Person ooxx(String str, int i) {
        return new Person(str,i);
    }
}
