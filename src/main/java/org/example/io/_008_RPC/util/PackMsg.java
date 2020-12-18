package org.example.io._008_RPC.util;

import lombok.Data;
import org.example.io._008_RPC.rpc.protocol.MyContent;
import org.example.io._008_RPC.rpc.protocol.MyHeader;

/**
 * @Author: Derek
 * @DateTime: 2020/12/18 18:11
 * @Description: TODO
 */
@Data
public class PackMsg {
    MyHeader header;
    MyContent content;

    public PackMsg(MyHeader header, MyContent content) {
        this.header = header;
        this.content = content;
    }

}

