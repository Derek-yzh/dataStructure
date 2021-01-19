package org.example.io._008_RPC.rpc;

import org.example.io._008_RPC.util.PackMsg;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Derek
 * @DateTime: 2020/12/18 18:03
 * @Description: ResponseMappingCallback
 */
public class ResponseMappingCallback {
    static ConcurrentHashMap<Long, CompletableFuture<Object>> mapping = new ConcurrentHashMap<>();

    public static void addCallBack(long requestID, CompletableFuture<Object> cb){
        mapping.putIfAbsent(requestID,cb);
    }

    public static void runCallBack(PackMsg responsePkg){
        CompletableFuture<Object> cf = mapping.get(responsePkg.getHeader().getRequestID());
        cf.complete(responsePkg.getContent().getRes());
        remove(responsePkg.getHeader().getRequestID());
    }

    private static void remove(long requestID) {
        mapping.remove(requestID);
    }
}
