package org.example.activeMq.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Derek
 * @DateTime: 2020/12/20 16:04
 * @Description: 用于了解使用：createObjectMessage
 */
@Data
public class MQ implements Serializable {
    String name;
    int size;

    public MQ(String name, int size) {
        this.name = name;
        this.size = size;
    }
}
