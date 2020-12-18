package org.example.io._008_RPC.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @Author: Derek
 * @DateTime: 2020/12/18 17:27
 * @Description: TODO
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