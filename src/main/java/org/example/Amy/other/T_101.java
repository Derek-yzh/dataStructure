package org.example.Amy.other;

import org.example.A_T.common.Node;

/**
 * @Author: Derek
 * @DateTime: 2020/11/29 19:58
 * @Description: TODO
 */
public class T_101 {

    public static void main(String[] args) {

        Node head = new Node(4);
        head.left = new Node(5);
        head.right = new Node(6);

        pre(head);

    }

    public static void pre(Node head){
        if (head == null){
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }

    public static void in(Node head){
        if (head == null){
            return;
        }
        pre(head.left);
        System.out.println(head.value);
        pre(head.right);
    }

    public static void pos(Node head){
        if (head == null){
            return;
        }
        pre(head.left);
        pre(head.right);
        System.out.println(head.value);
    }

}
