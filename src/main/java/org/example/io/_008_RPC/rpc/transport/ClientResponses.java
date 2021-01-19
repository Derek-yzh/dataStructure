package org.example.io._008_RPC.rpc.transport;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.example.io._008_RPC.rpc.ResponseMappingCallback;
import org.example.io._008_RPC.util.PackMsg;


/**
 * @Author: Derek
 * @DateTime: 2020/12/18 18:18
 * @Description: ClientResponses
 */
public class ClientResponses extends ChannelInboundHandlerAdapter {
    //consumer...
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        PackMsg responsePkg = (PackMsg) msg;
        ResponseMappingCallback.runCallBack(responsePkg);
    }
}
