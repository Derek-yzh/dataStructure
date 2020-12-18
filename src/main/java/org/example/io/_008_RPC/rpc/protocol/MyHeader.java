package org.example.io._008_RPC.rpc.protocol;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * @Author: Derek
 * @DateTime: 2020/12/18 16:49
 * @Description: TODO
 */
@Data
public class MyHeader implements Serializable {
    /**
     * 通信协议
     * 1.ooxx值
     * 2.UUID
     * 3.DATA_LEN
     */
    int flag; //32位可以设置很多信息...
    long requestID;
    long dataLen;

    public MyHeader(int flag, long requestID, long dataLen) {
        this.flag = flag;
        this.requestID = requestID;
        this.dataLen = dataLen;
    }

    public MyHeader() {
    }

    public static MyHeader createHeader(byte[] msgBody) {
        int f = 0x14141414; //0x14 0001 0100
        long requestId = Math.abs(UUID.randomUUID().getLeastSignificantBits());
        int size = msgBody.length;
        return new MyHeader(f,requestId,size);
    }


}
