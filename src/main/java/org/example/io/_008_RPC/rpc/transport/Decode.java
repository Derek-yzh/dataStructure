package org.example.io._008_RPC.rpc.transport;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.example.io._008_RPC.rpc.protocol.MyContent;
import org.example.io._008_RPC.rpc.protocol.MyHeader;
import org.example.io._008_RPC.util.PackMsg;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * @Author: Derek
 * @DateTime: 2020/12/18 18:11
 * @Description: Decode
 */
public class Decode extends ByteToMessageDecoder {

    /**
     * netty自带类 ByteToMessageDecoder  (偷懒自己不用写了)
     * @param ctx 上下文
     * @param buf 父类里一定有channelRead{ 前后的拼buf decode() 剩余留余 对out遍历} -> byteBuf
     * @param out 解码完的对象
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {

        while (buf.readableBytes() >= 115){

            //get header
            byte[] bytes = new byte[115];
            buf.getBytes(buf.readerIndex(),bytes); //geyBytes不会移动指针
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            ObjectInputStream oin = new ObjectInputStream(in);
            MyHeader header = (MyHeader) oin.readObject();

            //Decode在两个方向都使用  通信的协议
            if (buf.readableBytes()-115 >= header.getDataLen()){

                //get data
                buf.readBytes(115); //处理指针 指针移动到data开始的位置
                byte[] data = new byte[(int)header.getDataLen()];
                buf.readBytes(data);
                ByteArrayInputStream din = new ByteArrayInputStream(data);
                ObjectInputStream doin = new ObjectInputStream(din);
                if (header.getFlag() == 0x14141414){ //in
                    MyContent content = (MyContent) doin.readObject();
                    out.add(new PackMsg(header,content));
                }else if (header.getFlag() == 0x14141424){ //out
                    MyContent content = (MyContent) doin.readObject();
                    out.add(new PackMsg(header,content));
                }

            }else {
                break;
            }

        }
    }
}
