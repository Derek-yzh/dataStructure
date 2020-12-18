package org.example.io;

import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * test file io
 * 需要用虚拟机linux系统
 */
public class _001_OSFileIO {

    static byte[] data = "123456789\n".getBytes();
    static String path =  "/root/testfileio/out.txt";


    public static void main(String[] args) throws Exception {


        switch ( args[0]) {
            case "0" :
                testBasicFileIO();
                break;
            case "1":
                testBufferedFileIO();
                break;
            case "2" :
                testRandomAccessFileWrite();
            case "3":
//                whatByteBuffer();
            default:

        }
    }


    //最基本的file写
    //10字节一次系统调用
    public static  void testBasicFileIO() throws Exception {
        File file = new File(path);
        FileOutputStream out = new FileOutputStream(file);
        while(true){
            Thread.sleep(10);
            out.write(data);

        }

    }

    //测试buffer文件IO
    //  jvm  8kB   syscall  write(8KByte[])   ：8K一次系统调用
    public static void testBufferedFileIO() throws Exception {
        File file = new File(path);
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        while(true){
            Thread.sleep(10);
            out.write(data);
        }
    }



    //测试文件NIO
    public static void testRandomAccessFileWrite() throws  Exception {
        RandomAccessFile raf = new RandomAccessFile(path, "rw");

        raf.write("hello mashibing\n".getBytes());
        raf.write("hello seanzhou\n".getBytes());
        System.out.println("write------------");
        System.in.read();

        raf.seek(4);
        raf.write("ooxx".getBytes());

        System.out.println("seek---------");
        System.in.read();

        FileChannel rafChannel = raf.getChannel();
        //mmap  堆外  和文件映射的   byte  not  object
        MappedByteBuffer map = rafChannel.map(FileChannel.MapMode.READ_WRITE, 0, 4096);

        map.put("@@@".getBytes());  //不是系统调用  但是数据会到达内核的pageCache
            //曾经我们是需要out.write()  这样的系统调用，才能让程序的data进入内核的pageCache
            //曾经必须有用户态内核态切换
            //mmap的内存映射，依然是内核的pageCache体系所约束的！！！
            //换言之，丢数据

            //你可以去github上找一些 其他C程序员写的jni扩展库，使用linux内核的Direct IO
            //Direct IO(直接IO)是忽略linux的pageCache
            //是把pageCache交给了程序自己开辟一个字节数组当作pageCache，动用代码逻辑来维护一致性/dirty。。。一系列复杂问题
            //数据库一般使用直接IO

        System.out.println("map--put--------");
        System.in.read();

        //map.force();//类似于flush

        raf.seek(0);

        ByteBuffer buffer = ByteBuffer.allocate(8192);
        //ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        int read = rafChannel.read(buffer);//buffer.put()
        System.out.println(buffer);
        buffer.flip();
        System.out.println(buffer);

        for (int i = 0; i < buffer.limit(); i++) {
            Thread.sleep(200);
            System.out.print(((char)buffer.get(i)));
        }


    }

    /**
     * 测试ByteBuffer
     *
     * 想读的时候buffer.flip();
     * 想写的时候buffer.compact();
     */
    @Test
    public  void whatByteBuffer(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //ByteBuffer buffer = ByteBuffer.allocateDirect(1024);//堆外


        System.out.println("postition: " + buffer.position());
        System.out.println("limit: " +  buffer.limit());
        System.out.println("capacity: " + buffer.capacity());
        System.out.println("mark: " + buffer);

        buffer.put("123".getBytes());

        System.out.println("-------------put:123......");
        System.out.println("mark: " + buffer);

        buffer.flip();   //读写交替

        System.out.println("-------------flip......");
        System.out.println("mark: " + buffer);

        buffer.get();

        System.out.println("-------------get......");
        System.out.println("mark: " + buffer);

        buffer.compact();

        System.out.println("-------------compact......");
        System.out.println("mark: " + buffer);

        buffer.clear();

        System.out.println("-------------clear......");
        System.out.println("mark: " + buffer);

    }


}
