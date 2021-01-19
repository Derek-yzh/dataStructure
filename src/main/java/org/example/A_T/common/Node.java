package org.example.A_T.common;

import lombok.Data;

/**
 * @Author: Derek
 * @DateTime: 2020/11/27 11:47
 * @Description: TODO
 */
@Data
public class Node {
    public int value;
    public Node left;
    public Node right;
    public Node parent;

    public Node(int data) {
        this.value = data;
    }

}
