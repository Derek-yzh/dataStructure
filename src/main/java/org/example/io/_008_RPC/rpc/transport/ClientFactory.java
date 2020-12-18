package org.example.io._008_RPC.rpc.transport;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.example.io._008_RPC.rpc.ResponseMappingCallback;
import org.example.io._008_RPC.rpc.protocol.MyContent;
import org.example.io._008_RPC.rpc.protocol.MyHeader;
import org.example.io._008_RPC.util.SerDerUtil;

import java.net.InetSocketAddress;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Derek
 * @DateTime: 2020/12/18 17:45
 * @Description: ClientFactory
 */
@SuppressWarnings("all")
public class ClientFactory {

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

    public static CompletableFuture<Object> transport(MyContent content) throws InterruptedException {
        byte[] msgBody = SerDerUtil.ser(content);
        MyHeader header = MyHeader.createHeader(msgBody);
        byte[] msgHeader = SerDerUtil.ser(header);
        System.out.println("msgHeader :"+msgHeader.length);

        NioSocketChannel clientChannel = factory.getClient(new InetSocketAddress("localhost", 9090));

        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(msgHeader.length + msgBody.length);
        long id = header.getRequestID();
        CompletableFuture<Object> res = new CompletableFuture<>();
        ResponseMappingCallback.addCallBack(id,res);

        byteBuf.writeBytes(msgHeader);
        byteBuf.writeBytes(msgBody);
        ChannelFuture channelFuture = clientChannel.writeAndFlush(byteBuf);
        channelFuture.sync();
        return res;
    }

    ConcurrentHashMap<InetSocketAddress, ClientPool> outboxes = new ConcurrentHashMap<>();

    public synchronized NioSocketChannel getClient(InetSocketAddress address){

        //TODO 并发下一定要谨慎  :双重检查
        ClientPool clientPool = outboxes.get(address);
        if (clientPool == null){
            synchronized (outboxes) {
                if (clientPool == null){
                    outboxes.put(address, new ClientPool(poolSize));
                    clientPool = outboxes.get(address);
                }
            }
        }

        int i = rand.nextInt(poolSize);

        if (clientPool.clients[i] != null && clientPool.clients[i].isActive()){
            return clientPool.clients[i];
        }else {
            synchronized (clientPool.lock[i]){
                if (clientPool.clients[i] == null || !clientPool.clients[i].isActive()){
                    clientPool.clients[i] = create(address);
                }
            }
        }
        return clientPool.clients[i];
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
