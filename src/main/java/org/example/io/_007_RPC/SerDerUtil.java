package org.example.io._007_RPC;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @Author: Derek
 * @DateTime: 2020/12/18 10:57
 * @Description: 序列化
 */
public class SerDerUtil {

    static ByteArrayOutputStream out = new ByteArrayOutputStream();


    public synchronized static byte[] ser(Object msg){
        out.reset();
        ObjectOutputStream oout = null;
        byte[] msgBody = null;
        try {
            oout = new ObjectOutputStream(out);
            oout.writeObject(msg);
            msgBody = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msgBody;
    }

}
