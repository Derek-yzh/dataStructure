package org.example.io._008_RPC.rpc.transport;

import org.example.io._008_RPC.rpc.Dispatcher;
import org.example.io._008_RPC.rpc.protocol.MyContent;
import org.example.io._008_RPC.util.SerDerUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;

/**
 * @Author: Derek
 * @DateTime: 2020/12/18 22:09
 * @Description: TODO
 */
public class MyHttpRpcHandler extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream in = req.getInputStream();
        ObjectInputStream oin = new ObjectInputStream(in);
        try {
            MyContent myContent = (MyContent) oin.readObject();

            //执行方法
            String serviceName = myContent.getName();
            String methodName = myContent.getMethodName();
            Object c = Dispatcher.getDis().get(serviceName);
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
            //byte[] contentByte = SerDerUtil.ser(resContent);

            ServletOutputStream out = resp.getOutputStream();
            ObjectOutputStream oout = new ObjectOutputStream(out);
            oout.writeObject(resContent);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
