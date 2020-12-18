package org.example.io._007_RPC;

import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Data;

/**
 * @Author: Derek
 * @DateTime: 2020/12/18 9:33
 * @Description: TODO
 */
@Data
class ClientPool{
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
