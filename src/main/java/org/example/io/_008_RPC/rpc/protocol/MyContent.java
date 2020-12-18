package org.example.io._008_RPC.rpc.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Derek
 * @DateTime: 2020/12/18 16:48
 * @Description: TODO
 */
@Data
public class MyContent implements Serializable {
    String name; //接口名称
    String methodName;
    Class<?>[] parameterTypes;
    Object[] args;

    Object res; //返回值

    public MyContent(String name, String methodName, Class<?>[] parameterTypes, Object[] args) {
        this.name = name;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.args = args;
    }

    public MyContent() {
    }
}
