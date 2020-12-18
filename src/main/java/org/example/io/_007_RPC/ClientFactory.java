package org.example.io._007_RPC;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Derek
 * @DateTime: 2020/12/18 9:30
 * @Description: ClientFactory
 */
class ClientFactory{

    //连接池clients数量
    int poolSize = 10;

    Random rand = new Random();

    NioEventLoopGroup clientWorker;

    //factory单例
    private static final ClientFactory factory;
    static {
        factory = new ClientFactory();
    }
    private ClientFactory(){}
    public static ClientFactory getFactory(){
        return factory;
    }

    //一个Consumer可以连接多个provider，每一个provider都有自己的pool  K,V
    ConcurrentHashMap<InetSocketAddress,ClientPool> outboxes = new ConcurrentHashMap<>();

    public synchronized NioSocketChannel getClient(InetSocketAddress address){
        ClientPool clientPool = outboxes.get(address);
        if (clientPool == null){
            outboxes.put(address,new ClientPool(poolSize));
            clientPool = outboxes.get(address);
        }
        int i = rand.nextInt(poolSize);
        if (clientPool.clients[i] != null && clientPool.clients[i].isActive()){
            return clientPool.clients[i];
        }
        synchronized (clientPool.lock[i]){
            return clientPool.clients[i] = create(address);
        }
    }

    private NioSocketChannel create(InetSocketAddress address) {
        //基于Netty的客户端创建方式
        clientWorker = new NioEventLoopGroup();
        Bootstrap bs = new Bootstrap();
        ChannelFuture connect = bs.group(clientWorker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new Decode());
                        p.addLast(new ClientResponses());
                    }
                })
                .connect(address);
        try {
            NioSocketChannel client = (NioSocketChannel)connect.sync().channel();
            return client;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
