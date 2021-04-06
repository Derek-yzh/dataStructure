package org.example.io._005_testReactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Derek
 * @DateTime: 2020/12/17 12:00
 * @Description: SelectorTreadGroup
 */
public class SelectorTreadGroup {

    SelectorThread[] boss;
    SelectorThread[] worker;
    ServerSocketChannel server = null;
    AtomicInteger xid = new AtomicInteger(-1);

    public SelectorTreadGroup(int bossNum,int workerNumber) {
        boss = new SelectorThread[bossNum];
        for (int i = 0; i < bossNum; i++) {
            boss[i] = new SelectorThread(this);
            new Thread(boss[i]).start();
        }
        worker = new SelectorThread[workerNumber];
        for (int i = 0; i < workerNumber; i++) {
            worker[i] = new SelectorThread(this);
            new Thread(worker[i]).start();
        }
    }

    public void bind(int port) {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));

            //注册到哪个selector上呢？
            nextSelector(server);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**\
     * 选择一个selector放到它的队列中 进行注册
     * 无论serverSocket or Socket 都复用这个方法
     * @param c Channel
     */
    public void nextSelector(Channel c) {
        try {
            SelectorThread st = null;
            if (c instanceof ServerSocketChannel)   st = next(boss); //server
            else    st = next(worker); //client
            //1.通过队列传递数据 消息
            st.lbq.put(c);
            // 2.通过打断阻塞，让对应的线程自己在打断后完成注册selector
            st.selector.wakeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private SelectorThread next(SelectorThread[] sts) {
        int index = xid.incrementAndGet() % sts.length;
        return sts[index];
    }

}
