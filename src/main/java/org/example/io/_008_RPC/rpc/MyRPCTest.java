package org.example.io._008_RPC.rpc;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.example.io._008_RPC.proxy.MyProxy;
import org.example.io._008_RPC.rpc.protocol.MyContent;
import org.example.io._008_RPC.rpc.service.Car;
import org.example.io._008_RPC.rpc.service.Fly;
import org.example.io._008_RPC.rpc.service.Person;
import org.example.io._008_RPC.rpc.service.impl.MyCar;
import org.example.io._008_RPC.rpc.service.impl.MyFly;
import org.example.io._008_RPC.rpc.transport.MyHttpRpcHandler;
import org.example.io._008_RPC.util.SerDerUtil;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Derek
 * @DateTime: 2020/12/18 18:44
 * @Description: RPCTest
 */
@SuppressWarnings("all")
public class MyRPCTest {

    /**
     * 模拟server端
     */
    @Test
    public void startServer(){

        MyCar car = new MyCar();
        MyFly fly = new MyFly();
        Dispatcher dis = Dispatcher.getDis();
        dis.register(Car.class.getName(),car);
        dis.register(Fly.class.getName(),fly);

        NioEventLoopGroup boss = new NioEventLoopGroup(20); //netty线程数量
        NioEventLoopGroup worker = boss;

        ServerBootstrap sbs = new ServerBootstrap();
        ChannelFuture bind = sbs.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        System.out.println("server accept client port: "+ch.remoteAddress().getPort());
                        ChannelPipeline p = ch.pipeline();

                        //1.自定义的rpc
                        //p.addLast(new Decode());
                        //p.addLast(new ServerRequestHandler(dis));
                        //自己定义协议关注的问题：粘包拆包的问题，header+content

                        //2.http传输协议 可以自己学解码，其实netty提供了一套编解码
                        p.addLast(new HttpServerCodec()); //netty提供的一套编解码
                        p.addLast(new HttpObjectAggregator(1024*512));
                        p.addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                //http协议，这个msg是一个：完整的httpRequest
                                FullHttpRequest request = (FullHttpRequest) msg;
                                System.out.println(request.toString());

                                //这个就是consumer序列化的MyContent
                                ByteBuf content = request.content();
                                byte[] data = new byte[content.readableBytes()];
                                content.readBytes(data);
                                ObjectInputStream oin = new ObjectInputStream(new ByteArrayInputStream(data));
                                MyContent myContent = (MyContent) oin.readObject();

                                //执行方法
                                String serviceName = myContent.getName();
                                String methodName = myContent.getMethodName();
                                Object c = dis.get(serviceName);
                                Class<?> clazz = c.getClass();
                                Object res = null;
                                try {
                                    Method m = clazz.getMethod(methodName, myContent.getParameterTypes());
                                    res = m.invoke(c, myContent.getArgs());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                MyContent resContent = new MyContent();
                                resContent.setRes(res);
                                byte[] contentByte = SerDerUtil.ser(resContent);

                                DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0,
                                        HttpResponseStatus.OK, Unpooled.copiedBuffer(contentByte));
                                response.headers().set(HttpHeaderNames.CONTENT_LENGTH,contentByte.length);
                                //http协议，header+body
                                ctx.writeAndFlush(response);
                            }
                        });


                    }
                }).bind(new InetSocketAddress("localhost", 9090));
        try {
            ChannelFuture sync = bind.sync();
            sync.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void startHttpServer(){

        MyCar car = new MyCar();
        MyFly fly = new MyFly();
        Dispatcher dis = Dispatcher.getDis();
        dis.register(Car.class.getName(),car);
        dis.register(Fly.class.getName(),fly);

        //tomcat jetty
        Server server = new Server(new InetSocketAddress("localhost", 9090));
        ServletContextHandler handler = new ServletContextHandler(server, "/");
        server.setHandler(handler);
        handler.addServlet(MyHttpRpcHandler.class,"/*"); //web.xml

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 模拟consumer端
     */
    @Test
    public void get(){

        /*new Thread(() -> {
            startServer();
        },"A").start();
        System.out.println("server started......");*/

        AtomicInteger num = new AtomicInteger(0);
        int size = 50;
        Thread[] threads = new Thread[size];
        for (int i = 0; i < size; i++) {
            threads[i] = new Thread(() -> {
                Car car = MyProxy.proxyGet(Car.class); //基于动态代理实现 //是真的要触发rpc吗
                String arg = "hello_" + num.incrementAndGet();
                String res = car.ooxx(arg);
                System.out.println("client over msg: "+res+" src arg: "+arg);
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * http协议下的RPC
     */
    @Test
    public void testRPC(){
        Fly fly = MyProxy.proxyGet(Fly.class);
        Person p = fly.ooxx("zhangsan",16);
        System.out.println(p);
    }

}
