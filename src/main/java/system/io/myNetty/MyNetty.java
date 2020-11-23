package system.io.myNetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;

/**
 * @Author: Derek
 * @DateTime: 2020/9/12 8:52
 * @Description: netty
 */
public class MyNetty {

    public static void main(String[] args) throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        Bootstrap bs = new Bootstrap();
        ChannelFuture connect = bs.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>(){
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline p = socketChannel.pipeline();
                        p.addLast(new MyInHandler());
                    }
                })
                .connect(new InetSocketAddress("192.168.74.11",9090));
        Channel client = connect.sync().channel();

        ByteBuf buf = Unpooled.copiedBuffer("hello server".getBytes());
        ChannelFuture send = client.writeAndFlush(buf);

        client.closeFuture().sync();

    }

    @Test
    public void nettyServer() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        ServerBootstrap bs = new ServerBootstrap();
        ChannelFuture bind = bs.group(group, group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        ChannelPipeline p = nioSocketChannel.pipeline();
                        p.addLast(new MyInHandler());
                    }
                })
                .bind(new InetSocketAddress("192.168.74.11", 9090));
            bind.sync().channel().closeFuture().sync();

    }

}

@ChannelHandler.Sharable
class MyInHandler extends ChannelInboundHandlerAdapter{
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
        SocketChannel client = (SocketChannel) msg;

    }
}




