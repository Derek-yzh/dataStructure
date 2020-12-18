package org.example.io._006_myNetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.*;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Derek
 * @DateTime: 2020/12/17 16:18
 * @Description: MyNetty
 */
@SuppressWarnings("all")
public class MyNetty {

    /**
     * 官方写法nettyServer
     */
    @Test
    public void nettyServer() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        ServerBootstrap bs = new ServerBootstrap();
        ChannelFuture bind = bs.group(group, group)
                .channel(NioServerSocketChannel.class)
                //.childHandler(new ChannelInit())
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new MyInHandler());
                    }
                })
                .bind(new InetSocketAddress("192.168.74.1", 9090));
        bind.sync().channel().closeFuture().sync();
    }

    /**
     * 官方写法nettyClient
     */
    @Test
    public void nettyClient() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        Bootstrap bs = new Bootstrap();
        ChannelFuture connect = bs.group(group)
                .channel(NioSocketChannel.class)
                //.handler(new ChannelInit())
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new MyInHandler());
                    }
                })
                .connect(new InetSocketAddress("192.168.74.31", 9090));
        Channel client = connect.sync().channel();

        ByteBuf buf = Unpooled.copiedBuffer("hello server".getBytes());
        ChannelFuture send = client.writeAndFlush(buf);
        send.sync();

        client.closeFuture().sync();
    }

    /**
     * server端
     * @throws InterruptedException
     */
    @Test
    public void serverMode() throws InterruptedException {
        NioEventLoopGroup thread = new NioEventLoopGroup(1);
        NioServerSocketChannel server = new NioServerSocketChannel();

        thread.register(server);
        //指不定什么时候来client。。。响应式
        ChannelPipeline p = server.pipeline();
        //p.addLast(new MyAcceptHandler(thread,new MyInHandler())); //accept接受客户端注册
        p.addLast(new MyAcceptHandler(thread,new ChannelInit())); //accept接受客户端注册
        ChannelFuture bind = server.bind(new InetSocketAddress("192.168.74.1", 9090));


        bind.sync().channel().closeFuture().sync();
        System.out.println("server close");
    }

    /**
     * 客户端
     * 连接别人
     * 1.主动发送数据
     * 2.别人什么时候给我发？ event selector
     */
    @Test
    public void clientMode() throws InterruptedException {
        NioEventLoopGroup thread = new NioEventLoopGroup(2);

        //客户端模式
        NioSocketChannel client = new NioSocketChannel();

        thread.register(client); //epoll_ctl(5,ADD,3)

        //响应式：
        ChannelPipeline p = client.pipeline();
        p.addLast(new MyInHandler());

        //reactor 异步的特征
        ChannelFuture connect = client.connect(new InetSocketAddress("192.168.74.31", 9090));
        ChannelFuture sync = connect.sync();

        ByteBuf buf = Unpooled.copiedBuffer("hello server".getBytes());
        ChannelFuture send = client.writeAndFlush(buf);
        send.sync();

        sync.channel().closeFuture().sync();
        System.out.println("client over...");
    }

    @Test
    public void loopExecutor(){
        NioEventLoopGroup selector = new NioEventLoopGroup(2);
        selector.execute(()->{
            for(;;){
                System.out.println("hello world001!");
                try { TimeUnit.MILLISECONDS.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
            }
        });
        selector.execute(()->{
            for(;;){
                System.out.println("hello world002!");
                try { TimeUnit.MILLISECONDS.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
            }
        });

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void myByteBuf(){
        //ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(8, 20);

        //pool
        //ByteBuf buf = UnpooledByteBufAllocator.DEFAULT.heapBuffer(8, 20);
        ByteBuf buf = PooledByteBufAllocator.DEFAULT.directBuffer(8, 20);

        print(buf);

        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);
        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);
        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);
        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);
        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);


    }

    public static void print(ByteBuf buf){
        System.out.println("buf.isReadable()    :\t"+buf.isReadable());
        System.out.println("buf.readerIndex()   :\t"+buf.readerIndex());
        System.out.println("buf.readableBytes() :\t"+buf.readableBytes());

        System.out.println("buf.isWritable()    :\t"+buf.isWritable());
        System.out.println("buf.writerIndex()   :\t"+buf.writerIndex());
        System.out.println("buf.writableBytes() :\t"+buf.writableBytes());

        System.out.println("buf.capacity()      :\t"+buf.capacity());
        System.out.println("buf.maxCapacity()   :\t"+buf.maxCapacity());

        System.out.println("buf.isDirect()      :\t"+buf.isDirect());

        System.out.println("-----------------------------------------------");
    }

    /**
     * 为啥要一个initHandler，可以没有，但是MyInHandler需要设计为单例
     */
    @ChannelHandler.Sharable
    class ChannelInit extends ChannelInboundHandlerAdapter{
        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            Channel client = ctx.channel();
            ChannelPipeline p = client.pipeline();
            p.addLast(new MyInHandler());//2.client::pipeLine[ChannelInit,MyInHandler]
            ctx.pipeline().remove(this);
        }
    }

    /**
     * 用户自己实现, 你说能让用户放弃属性的操作吗  MyInHandler需要是单例的
     * @ChannelHandler.Sharable 这个不应该强压给客户端
     */
    class MyInHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("client register...");
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("client active...");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            //CharSequence str = buf.readCharSequence(buf.writableBytes(), CharsetUtil.UTF_8); //read完buf会被读完 指针移动
            CharSequence str = buf.getCharSequence(0,buf.writableBytes(), CharsetUtil.UTF_8);
            System.out.println(str);

            ctx.writeAndFlush(buf);
        }
    }

    class MyAcceptHandler extends ChannelInboundHandlerAdapter{

        private final EventLoopGroup selector;
        private final ChannelHandler handler;
        public MyAcceptHandler(EventLoopGroup thread, ChannelHandler myInHandler) {
            this.selector = thread;
            this.handler = myInHandler;
        }

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("server register...");
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("server active...");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            SocketChannel client = (SocketChannel) msg; //accept框架做了

            //2.响应式的handler
            ChannelPipeline p = client.pipeline();
            p.addLast(handler);//1.client::pipeLine[ChannelInit,]

            //1.注册
            selector.register(client);
        }
    }

}
