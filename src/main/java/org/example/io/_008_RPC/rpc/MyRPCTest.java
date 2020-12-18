package org.example.io._008_RPC.rpc;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.example.io._008_RPC.proxy.MyProxy;
import org.example.io._008_RPC.rpc.service.Car;
import org.example.io._008_RPC.rpc.service.Fly;
import org.example.io._008_RPC.rpc.service.impl.MyCar;
import org.example.io._008_RPC.rpc.service.impl.MyFly;
import org.example.io._008_RPC.rpc.transport.Decode;
import org.example.io._008_RPC.rpc.transport.ServerRequestHandler;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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
                        p.addLast(new Decode());
                        p.addLast(new ServerRequestHandler(dis));
                    }
                }).bind(new InetSocketAddress("localhost", 9090));
        try {
            ChannelFuture sync = bind.sync();
            sync.channel().closeFuture().sync();

        } catch (InterruptedException e) {
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
}
