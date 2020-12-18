package org.example.io._007_RPC;

import lombok.Data;

/**
 * @Author: Derek
 * @DateTime: 2020/12/18 10:20
 * @Description: 封装 header+content
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
