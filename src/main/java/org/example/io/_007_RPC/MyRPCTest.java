package org.example.io._007_RPC;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Derek
 * @DateTime: 2020/12/17 20:35
 * @Description:
 *
 *      1.先假设一个需求。写一个RPC
 *      2.来回通信，连接数量，拆包？
 *      3.动态代理，序列化，协议封装
 *      4.连接池
 *      5.就像调用本地方法一样调用远程方法，面向java就是所谓的 面向接口开发
 *
 */
@SuppressWarnings("all")
public class MyRPCTest {



    /**
     * 模拟server端
     */
    public void startServer(){

        MyCar car = new MyCar();
        MyFly fly = new MyFly();
        Dispatcher dis = new Dispatcher();
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

        new Thread(() -> {
            startServer();
        },"A").start();
        System.out.println("server started......");

        AtomicInteger num = new AtomicInteger(0);
        int size = 50;
        Thread[] threads = new Thread[size];
        for (int i = 0; i < size; i++) {
            threads[i] = new Thread(() -> {
                Car car = proxyGet(Car.class); //基于动态代理实现 //是真的要触发rpc吗
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

//        Fly fly = proxyGet(Fly.class);
//        fly.ooxx("hello");
    }

    public static <T>T proxyGet(Class<T> interfaceInfo) {
        //可以实现各个版本的动态代理
        ClassLoader loader = interfaceInfo.getClassLoader();
        Class<?>[] methodInfo = {interfaceInfo};

        return (T) Proxy.newProxyInstance(loader, methodInfo, (proxy, method, args) -> {
            //如何设计我们的consumer对于provider的调用过程

            //1.调用服务，方法，参数 -》 封装成message [content]
            String name = interfaceInfo.getName();
            String methodName = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            MyContent content = new MyContent(name, methodName, parameterTypes, args);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oout = new ObjectOutputStream(out);
            oout.writeObject(content);
            byte[] msgBody = out.toByteArray();

            //2.封装Header requestId + message， 本地要缓存
            //协议：[header<>]  [msgBody]
            MyHeader header = createHeader(msgBody);
            out.reset();
            oout = new ObjectOutputStream(out);
            oout.writeObject(header);
            //解决数据decode问题
            //TODO: Server: dispacher Excutor
            byte[] msgHeader = out.toByteArray();
            System.out.println("msgHeader :"+msgHeader.length);

            //3.连接池：取得连接
            ClientFactory factory = ClientFactory.getFactory();
            //获取连接过程中：开始-创建，过程
            NioSocketChannel clientChannel = factory.getClient(new InetSocketAddress("localhost", 9090));

            //4.发送 -》 走IO out --》 走Netty(event 驱动)
            ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(msgHeader.length + msgBody.length);

            long id = header.requestID;
            CompletableFuture<String> res = new CompletableFuture<>();
            ResponseMappingCallback.addCallBack(id,res);

            byteBuf.writeBytes(msgHeader);
            byteBuf.writeBytes(msgBody);
            ChannelFuture channelFuture = clientChannel.writeAndFlush(byteBuf);
            channelFuture.sync(); //io是双向的，你看似有个sync，它仅代表out

            //5. ？ 如果从IO，未来回来了，怎么将代码执行到这里   (睡眠/回调，如何让线程停下来)

            return res.get(); //阻塞的
        });
    }

    public static MyHeader createHeader(byte[] msgBody) {
        int f = 0x14141414; //0x14 0001 0100
        long requestId = Math.abs(UUID.randomUUID().getLeastSignificantBits());
        int size = msgBody.length;
        return new MyHeader(f,requestId,size);
    }


}

class ServerRequestHandler extends  ChannelInboundHandlerAdapter{

    Dispatcher dis;

    public ServerRequestHandler(Dispatcher dis) {
        this.dis = dis;
    }

    //provider
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        PackMsg requestPkg = (PackMsg) msg;

        //如果假设处理完了，要给客户端返回了！！
        //你需要注意那些细节?
        //byteBuf  requestID
        //在client那一侧也要解决解码问题
        //关注rpc通信协议 来的时候flag 0x14141414
        //有新的header+content

        String ioThreadName = Thread.currentThread().getName(); //io线程名称

        /**
         * 可选择处理业务
         * 1.直接在当前方法处理
         * 2.自己创建线程池
         * 3.使用netty的eventLoop处理
         * 3_1.ctx.executor().execute(()->{
         * 3_2.ctx.executor().parent().next().execute(()->{
         */
         //ctx.executor().execute(()->{
         ctx.executor().parent().next().execute(()->{
             String execThreadName = Thread.currentThread().getName();

             //执行方法
             String serviceName = requestPkg.content.getName();
             String methodName = requestPkg.content.getMethodName();
             Object c = dis.get(serviceName);
             Class<?> clazz = c.getClass();
             Object res = null;
             try {
                 Method m = clazz.getMethod(methodName, requestPkg.content.getParameterTypes());
                 res = m.invoke(c, requestPkg.content.getArgs());
             } catch (Exception e) {
                 e.printStackTrace();
             }

             //封装resHeader + resContent(含有RPC调用方法返回值)
             MyContent resContent = new MyContent();
             resContent.setRes(res);
             byte[] contentByte = SerDerUtil.ser(resContent);
             MyHeader resHeader = new MyHeader();
             resHeader.setRequestID(requestPkg.header.getRequestID());
             resHeader.setFlag(0x14141424);
             resHeader.setDataLen(contentByte.length);
             byte[] headerByte = SerDerUtil.ser(resHeader);

             ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(headerByte.length + contentByte.length);
             byteBuf.writeBytes(headerByte);
             byteBuf.writeBytes(contentByte);

             ctx.writeAndFlush(byteBuf);
         });
    }
}

class Decode extends ByteToMessageDecoder{

    /**
     * netty自带类 ByteToMessageDecoder  (偷懒自己不用写了)
     * @param ctx 上下文
     * @param buf 父类里一定有channelRead{ 前后的拼buf decode() 剩余留余 对out遍历} -> byteBuf
     * @param out 解码完的对象
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {

        while (buf.readableBytes() >= 102){

            //get header
            byte[] bytes = new byte[102];
            buf.getBytes(buf.readerIndex(),bytes); //geyBytes不会移动指针
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            ObjectInputStream oin = new ObjectInputStream(in);
            MyHeader header = (MyHeader) oin.readObject();

            //Decode在两个方向都使用  通信的协议
            if (buf.readableBytes()-102 >= header.getDataLen()){

                //get data
                buf.readBytes(102); //处理指针 指针移动到data开始的位置
                byte[] data = new byte[(int)header.getDataLen()];
                buf.readBytes(data);
                ByteArrayInputStream din = new ByteArrayInputStream(data);
                ObjectInputStream doin = new ObjectInputStream(din);
                if (header.getFlag() == 0x14141414){ //in
                    MyContent content = (MyContent) doin.readObject();
                    out.add(new PackMsg(header,content));
                }else if (header.getFlag() == 0x14141424){ //out
                    MyContent content = (MyContent) doin.readObject();
                    out.add(new PackMsg(header,content));
                }

            }else {
                break;
            }

        }
    }
}

class ResponseMappingCallback {
    static ConcurrentHashMap<Long,CompletableFuture> mapping = new ConcurrentHashMap<>();

    public static void addCallBack(long requestID, CompletableFuture cb){
        mapping.putIfAbsent(requestID,cb);
    }

    public static void runCallBack(PackMsg responsePkg){
        CompletableFuture cf = mapping.get(responsePkg.header.getRequestID());
        cf.complete(responsePkg.content.getRes());
        remove(responsePkg.header.getRequestID());
    }

    private static void remove(long requestID) {
        mapping.remove(requestID);
    }
}

class ClientResponses extends ChannelInboundHandlerAdapter{
    //consumer...
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        PackMsg responsePkg = (PackMsg) msg;
        ResponseMappingCallback.runCallBack(responsePkg);
    }
}

interface Car{
    String ooxx(String msg);
}
interface Fly{
    void ooxx(String msg);
}

class MyCar implements Car{
    @Override
    public String ooxx(String msg) {
        System.out.println("Car::ooxx 方法执行了...");
        return "server res "+msg;
    }
}

class MyFly implements Fly{
    @Override
    public void ooxx(String msg) {
        System.out.println("Fly::ooxx 方法执行了...");
    }
}

class Dispatcher{

    public static ConcurrentHashMap<String,Object> invokeMap = new ConcurrentHashMap<>();

    public void register(String k, Object v){
        invokeMap.put(k,v);
    }

    public Object get(String k){
        return invokeMap.get(k);
    }

}