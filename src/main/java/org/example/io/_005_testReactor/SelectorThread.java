package org.example.io._005_testReactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: Derek
 * @DateTime: 2020/12/17 11:34
 * @Description:
 *      每线程对应一个selector
 *      多线程多程序下 该程序的并发客户端被分配到多个selector上
 *      注意：每个客户端只绑定到其中一个selector上
 *      其实不会有交互问题
 */
public class SelectorThread extends ThreadLocal<LinkedBlockingQueue<Channel>> implements Runnable{

    Selector selector = null;
    LinkedBlockingQueue<Channel> lbq = new LinkedBlockingQueue<>();
    //LinkedBlockingQueue<Channel> lbq = get(); //lbq在接口或者类中是固定使用方式 逻辑写死了 你需要的是lbq每个线程有自己的独立对象
    SelectorTreadGroup stg;

    public SelectorThread(SelectorTreadGroup stg) {
        try {
            this.stg = stg;
            this.selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected LinkedBlockingQueue<Channel> initialValue() {
        return new LinkedBlockingQueue<>();//你要丰富的是这里！ pool...
    }

    @Override
    public void run() {
        //Loop:循环
        while (true){

            try {
                //1.select()
                //System.out.println(Thread.currentThread().getName()+"   :before selector..."+selector.keys().size());
                int nums = selector.select(); //阻塞  wakeup()
                //System.out.println(Thread.currentThread().getName()+"   :after selector..."+selector.keys().size());

                //2.处理selectKeys
                if (nums > 0){
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iter = keys.iterator();
                    while (iter.hasNext()){ //线性处理的过程
                        SelectionKey key = iter.next();
                        iter.remove();

                        //复杂，接受客户端的过程(接受之后要注册，多线程下，新的客户端要注册到哪里呢？)
                        if (key.isAcceptable()){
                            acceptHandler(key);
                        }else if (key.isReadable()){
                            readHandler(key);
                        }else if (key.isWritable()){
                            acceptHandler(key);
                        }

                    }

                }
                
                //3.处理一些task: lbq
                if (!lbq.isEmpty()){
                    Channel c = lbq.take();
                    if (c instanceof ServerSocketChannel){
                        ServerSocketChannel server = (ServerSocketChannel) c;
                        server.register(selector,SelectionKey.OP_ACCEPT);
                        System.out.println(Thread.currentThread().getName()+" register listen");
                    }else if (c instanceof SocketChannel){
                        SocketChannel client = (SocketChannel) c;
                        ByteBuffer buffer = ByteBuffer.allocateDirect(4098);
                        client.register(selector,SelectionKey.OP_READ,buffer);
                        System.out.println(Thread.currentThread().getName()+" register client: "+client.getRemoteAddress());
                    }
                }


            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void readHandler(SelectionKey key) {
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        SocketChannel client = (SocketChannel) key.channel();
        buffer.clear();
        while (true){
            try {
                int num = client.read(buffer);
                if (num > 0){//将读到的内容翻转，然后直接写出
                    buffer.flip();
                    while (buffer.hasRemaining()){
                        client.write(buffer);
                    }
                    buffer.clear();
                }else if (num == 0){
                    break;
                }else {
                    //客户端断开了
                    System.out.println("client: "+client.getRemoteAddress()+" closed...");
                    key.cancel();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void acceptHandler(SelectionKey key) {
        System.out.println("选择一个selector注册 acceptHandler()");
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        try {
            SocketChannel client = server.accept();
            client.configureBlocking(false);
            
            //选择一个selector注册
            stg.nextSelector(client);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
