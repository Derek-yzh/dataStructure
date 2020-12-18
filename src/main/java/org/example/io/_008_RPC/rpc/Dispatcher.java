package org.example.io._008_RPC.rpc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Derek
 * @DateTime: 2020/12/18 16:27
 * @Description: TODO
 */
public class Dispatcher {

    private Dispatcher(){}
    static Dispatcher dis = null;
    public static Dispatcher getDis() {
        return dis;
    }
    static {
        dis = new Dispatcher();
    }

    public static ConcurrentHashMap<String,Object> invokeMap = new ConcurrentHashMap<>();

    public void register(String k, Object v){
        invokeMap.put(k,v);
    }

    public Object get(String k){
        return invokeMap.get(k);
    }

}
