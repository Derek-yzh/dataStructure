package org.example.io._008_RPC.rpc.transport;

import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Author: Derek
 * @DateTime: 2020/12/18 17:45
 * @Description: ClientPool
 */
public class ClientPool {
    NioSocketChannel[] clients;
    Object[] lock;

    ClientPool(int size){
        clients = new NioSocketChannel[size];
        lock = new Object[size];
        for (int i = 0; i < size; i++) {
            clients[i] = new NioSocketChannel();
            lock[i] = new Object();
        }
    }
}
