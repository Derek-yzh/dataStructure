package org.example.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * 多路复用器
 *
 * 红黑树最大796712
 */
public class _004_SocketMultiplexingSingleThreadV1 {

    private ServerSocketChannel server = null;
    private Selector selector = null;//linux 多路复用器(select poll epoll) nginx event{}
    int port = 9090;

    public void initServer() {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));

            //如果在epoll模型下 open() -> epoll_create -> fd6
            selector = Selector.open();  //  select  poll  *epoll 优先选择:epoll

            //server 约等于 listen状态的fd4
            /**
             * select，poll： jvm中开辟一个数组fd放进去
             * epoll：epoll_ctl(fd6,ADD,fd4,EPOLLIN)
             */
            server.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        initServer();
        System.out.println("服务器启动了。。。。。");
        try {//死循环
            while (true) {
                Set<SelectionKey> keys = selector.keys();
                System.out.println(keys.size()+"   size");

                //1.调用多路复用器 epoll_wait
                /**
                 * select，poll： 内核select(fd4)
                 * epoll：epoll_wait
                 *
                 * 参数可以带时间：  没有时间，0:阻塞  有时间，超时
                 * select.wakeup() 结果返回0
                 */
                while (selector.select(500) > 0) {

                    //返回的有状态的fd集合
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();

                    //so 管你啥多路复用器，我还得一个个处理他们的R/W，同步好辛苦...
                    Iterator<SelectionKey> iter = selectionKeys.iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        iter.remove();//set集合，不移除会重复处理
                        if (key.isAcceptable()) {
                            //重点 如果要接受一个新的连接
                            //语义上，accept接受连接并返回新连接的FD对吧？
                            //select poll 内核没有空间，那么jvm保存和前面的fd4那个listen一起
                            //epoll 我们希望通过epoll_ctl把新的客户端注册到内核空间
                            acceptHandler(key);
                        } else if (key.isReadable()) {

                            readHandler(key);//在当前线程中，这个方法可能阻塞
                            //所以为什么提出了 IO Threads
                            //redis 是不是用了epoll，redis是不是有个io threads的概念
                            //tomcat 8,9版本 异步处理方式  在IO和处理上解耦
                        }

                        /**
                         * read一开始就注册  但write依赖以上关系 什么时候用什么时候注册
                         * 如果一开始就注册了写事件 进入死循环 一直调起
                         */
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void acceptHandler(SelectionKey key) {
        try {
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel client = ssc.accept();//来啦客户端 fd7
            client.configureBlocking(false);

            ByteBuffer buffer = ByteBuffer.allocate(8192);

            //调用了register
            /**
             * select，poll： jvm中开辟一个数组fd7放进去
             * epoll：epoll_ctl(fd6,ADD,fd7,EPOLLIN)
             */
            client.register(selector, SelectionKey.OP_READ, buffer);
            System.out.println("-------------------------------------------");
            System.out.println("新客户端：" + client.getRemoteAddress());
            System.out.println("-------------------------------------------");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readHandler(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();
        int read = 0;
        try {
            while (true) {
                read = client.read(buffer);
                if (read > 0) {
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        client.write(buffer);
                    }
                    buffer.clear();
                } else if (read == 0) {
                    break;
                } else {
                    client.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        _004_SocketMultiplexingSingleThreadV1 service = new _004_SocketMultiplexingSingleThreadV1();
        service.start();
    }
}
