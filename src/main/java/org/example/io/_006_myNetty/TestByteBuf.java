package org.example.io._006_myNetty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import org.junit.jupiter.api.Test;

/**
 * @Author: Derek
 * @DateTime: 2021/4/6 19:41
 * @Description: Test ByteBuf
 */
public class TestByteBuf {

    @Test
    public void myByteBuf(){
        //ByteBuf
        //buf = ByteBufAllocator.DEFAULT.buffer(8, 20);

        //pool
        //ByteBuf buf = UnpooledByteBufAllocator.DEFAULT.heapBuffer(8, 20);
        ByteBuf buf = PooledByteBufAllocator.DEFAULT.directBuffer(8, 20);

        print(buf);

        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);
        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);
        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);
        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);
        buf.writeBytes(new byte[]{1,2,3,4});
        print(buf);

    }


    public static void print(ByteBuf buf){
        System.out.println("buf.isReadable()    :\t"+buf.isReadable());
        System.out.println("buf.readerIndex()   :\t"+buf.readerIndex());
        System.out.println("buf.readableBytes() :\t"+buf.readableBytes());

        System.out.println("buf.isWritable()    :\t"+buf.isWritable());
        System.out.println("buf.writerIndex()   :\t"+buf.writerIndex());
        System.out.println("buf.writableBytes() :\t"+buf.writableBytes());

        System.out.println("buf.capacity()      :\t"+buf.capacity());
        System.out.println("buf.maxCapacity()   :\t"+buf.maxCapacity());

        System.out.println("buf.isDirect()      :\t"+buf.isDirect());

        System.out.println("-----------------------------------------------");
    }

}
