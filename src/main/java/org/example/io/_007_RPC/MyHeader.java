package org.example.io._007_RPC;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Derek
 * @DateTime: 2020/12/18 9:30
 * @Description: MyHeader
 */
@Data
class MyHeader implements Serializable {
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
}
