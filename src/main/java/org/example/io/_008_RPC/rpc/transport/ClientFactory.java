package org.example.io._008_RPC.rpc.transport;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import org.example.io._008_RPC.rpc.ResponseMappingCallback;
import org.example.io._008_RPC.rpc.protocol.MyContent;
import org.example.io._008_RPC.rpc.protocol.MyHeader;
import org.example.io._008_RPC.util.SerDerUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
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

        //content 就是货物 现在可以用自定义的rpc传输协议，也可以用http协议作为载体传输
        //我们先手工用了http协议作为载体，那这样是不是代表我们未来可以让provider是一个tomcat jetty 基于http协议的一个容器
        //有无状态来自于你使用什么协议，那么HTTP协议肯定是无状态，每个请求对应一个连接
        //dubbo是一个rpc框架 netty是一个io框架

        //String type = "rpc";
        String type = "http";
        CompletableFuture<Object> res = new CompletableFuture<>();

        if ("rpc".equals(type)){
            byte[] msgBody = SerDerUtil.ser(content);
            MyHeader header = MyHeader.createHeader(msgBody);
            byte[] msgHeader = SerDerUtil.ser(header);
            System.out.println("msgHeader :"+msgHeader.length);

            NioSocketChannel clientChannel = factory.getClient(new InetSocketAddress("localhost", 9090));

            ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(msgHeader.length + msgBody.length);
            long id = header.getRequestID();
            ResponseMappingCallback.addCallBack(id,res);

            byteBuf.writeBytes(msgHeader);
            byteBuf.writeBytes(msgBody);
            ChannelFuture channelFuture = clientChannel.writeAndFlush(byteBuf);
            channelFuture.sync();
            return res;
        }
        else if ("http".equals(type)){
            //使用http协议为载体
            //1.用URL现成的工具(包含了http的编解码，发送，socket，连接)
            //urlTransport(content,res);

            //2.自己操心： on netty  (io框架)+已经提供的http相关的编解码
            //TODO 设置 header 的 CONTENT_LENGTH (内容长度)
            nettyTransport(content,res);

        }

        return res;
    }

    private static void nettyTransport(MyContent content, CompletableFuture<Object> res) throws InterruptedException {
        //在这个执行之前 我们的server端 provider端已经开发完了，已经是on netty的合同谈判server了
        //现在做的是consumer端的代码修改 改成on netty的http client

        //每个请求对应一个连接
        //1.通过netty建立io 建立连接
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        Bootstrap bs = new Bootstrap();
        ChannelFuture future = bs.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new HttpClientCodec());
                        p.addLast(new HttpObjectAggregator(1024 * 512));
                        p.addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                //3.接收 预埋的处理 根据netty对socket io 事件的响应
                                FullHttpResponse response = (FullHttpResponse) msg;
                                System.out.println(response.toString());

                                ByteBuf resContent = response.content();
                                byte[] data = new byte[resContent.readableBytes()];
                                resContent.readBytes(data);

                                ObjectInputStream oin = new ObjectInputStream(new ByteArrayInputStream(data));
                                MyContent o = (MyContent) oin.readObject();

                                res.complete(o.getRes());
                            }
                        });
                    }
                }).connect("localhost", 9090);
        //2.发送
        Channel clientChannel = future.sync().channel();
        byte[] data = SerDerUtil.ser(content);
        DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0,
                HttpMethod.POST, "/", Unpooled.copiedBuffer(data));

        request.headers().set(HttpHeaderNames.CONTENT_LENGTH,data.length);
        clientChannel.writeAndFlush(request).sync();//作为client 向server端发送：http request
    }

    private static void urlTransport(MyContent content, CompletableFuture<Object> res) {

        Object o = null;
        try {
            URL url = new URL("http://localhost:9090/");

            HttpURLConnection hc = (HttpURLConnection) url.openConnection();

            //post
            hc.setRequestMethod("POST");
            hc.setDoOutput(true);
            hc.setDoInput(true);

            OutputStream out = hc.getOutputStream();
            ObjectOutputStream oout = new ObjectOutputStream(out);
            oout.writeObject(content); //这里没有真正发送

            if (hc.getResponseCode() == 200){
                InputStream in = hc.getInputStream();
                ObjectInputStream oin = new ObjectInputStream(in);
                MyContent myContent = (MyContent) oin.readObject();
                o = myContent.getRes();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        res.complete(o);
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
