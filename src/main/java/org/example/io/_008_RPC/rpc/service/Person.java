package org.example.io._008_RPC.rpc.service;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Derek
 * @DateTime: 2020/12/18 20:28
 * @Description: TODO
 */
@Data
public class Person implements Serializable {

    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
