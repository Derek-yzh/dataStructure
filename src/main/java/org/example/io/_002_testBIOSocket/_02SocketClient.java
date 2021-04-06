package org.example.io._002_testBIOSocket;

import java.io.*;
import java.net.Socket;

/**
 * 客户端
 */
public class _02SocketClient {

    public static void main(String[] args) {

        try {
            Socket client = new Socket("127.0.0.1",9090);

            client.setSendBufferSize(20);

            //true  延迟发送         askfjklsdjfjdskjf      会一起接收
            //false 不延迟发送 ask    fjkl  sdjf  jds   kjf 会分散接收
            client.setTcpNoDelay(false);

            OutputStream out = client.getOutputStream();

            InputStream in = System.in;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            while(true){
                System.out.print("请输入：");
                String line = reader.readLine();
                if(line != null ){
                    byte[] bb = line.getBytes();
                    for (byte b : bb) {
                        out.write(b);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
