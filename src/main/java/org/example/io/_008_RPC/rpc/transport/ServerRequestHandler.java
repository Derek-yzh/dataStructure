package org.example.io._008_RPC.rpc.transport;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.example.io._008_RPC.rpc.Dispatcher;
import org.example.io._008_RPC.rpc.protocol.MyContent;
import org.example.io._008_RPC.rpc.protocol.MyHeader;
import org.example.io._008_RPC.util.PackMsg;
import org.example.io._008_RPC.util.SerDerUtil;

import java.lang.reflect.Method;

/**
 * @Author: Derek
 * @DateTime: 2020/12/18 18:14
 * @Description: TODO
 */
public class ServerRequestHandler extends ChannelInboundHandlerAdapter {

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
            String serviceName = requestPkg.getContent().getName();
            String methodName = requestPkg.getContent().getMethodName();
            Object c = dis.get(serviceName);
            Class<?> clazz = c.getClass();
            Object res = null;
            try {
                Method m = clazz.getMethod(methodName, requestPkg.getContent().getParameterTypes());
                res = m.invoke(c, requestPkg.getContent().getArgs());
            } catch (Exception e) {
                e.printStackTrace();
            }

            //封装resHeader + resContent(含有RPC调用方法返回值)
            MyContent resContent = new MyContent();
            resContent.setRes(res);
            byte[] contentByte = SerDerUtil.ser(resContent);
            MyHeader resHeader = new MyHeader(0x14141424,requestPkg.getHeader().getRequestID(),contentByte.length);
            byte[] headerByte = SerDerUtil.ser(resHeader);

            ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(headerByte.length + contentByte.length);
            byteBuf.writeBytes(headerByte);
            byteBuf.writeBytes(contentByte);

            ctx.writeAndFlush(byteBuf);
        });
    }
}
