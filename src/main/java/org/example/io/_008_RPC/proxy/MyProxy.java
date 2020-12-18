package org.example.io._008_RPC.proxy;

import org.example.io._008_RPC.rpc.Dispatcher;
import org.example.io._008_RPC.rpc.protocol.MyContent;
import org.example.io._008_RPC.rpc.transport.ClientFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.CompletableFuture;

/**
 * @Author: Derek
 * @DateTime: 2020/12/18 16:19
 * @Description: proxy
 */
@SuppressWarnings("all")
public class MyProxy {

    public static <T>T proxyGet(Class<T> interfaceInfo) {
        //可以实现各个版本的动态代理
        ClassLoader loader = interfaceInfo.getClassLoader();
        Class<?>[] methodInfo = {interfaceInfo};

        //TODO: local remote 用到dispatcher 直接给你返回?还是本地调用的时候也代理一下?
        Dispatcher dis = Dispatcher.getDis();

        return (T) Proxy.newProxyInstance(loader, methodInfo, (proxy, method, args) -> {

            Object o = dis.get(interfaceInfo.getName());
            if (o != null){
                //走local 否则走rpc //插入一些插件的机会
                System.out.println("local FC ......");
                Class<?> clazz = o.getClass();
                Object res = null;
                try {
                    Method m = clazz.getMethod(method.getName(), method.getParameterTypes());
                    res = m.invoke(o, args);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return res;
            }

            //TODO rpc 就像小火车拉货 content是service的具体实现，但还是需要header层完成IO的传输控制
            String name = interfaceInfo.getName();
            String methodName = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            MyContent content = new MyContent(name, methodName, parameterTypes, args);

            //TODO 未来的小火车可能会变->协议可能变

            /**
             * 1.缺失了注册表，zk
             * 2.第一层负载：面向provider LB
             * 3.consumer 线程池 面向service：并发就会有木桶效应，倾斜
             * serviceA
             *      ipA:port
             *          socket1
             *          socket2
             *      ipB:port
             *
             */
            CompletableFuture res = ClientFactory.transport(content);
            return res.get();
        });
    }

}
