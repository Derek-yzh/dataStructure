package org.example.A_T.Top_interview.T5;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: Derek
 * @DateTime: 2020/12/21 10:02
 * @Description:
 *
 * 双向链表节点结构和二叉树节点结构是一样的，如果你把last认为是left，next认为是next的话。
 * 给定一个搜索二叉树的头节点head，请转化成一条有序的双向链表，并返回链表的头节点。
 *
 */
public class T_002 {

    public static void main(String[] args) {

    }

    public static Node convert1(Node head) {
        Queue<Node> queue = new LinkedList<Node>();
        inOrderToQueue(head, queue);
        if (queue.isEmpty()) {
            return head;
        }
        head = queue.poll();
        Node pre = head;

        pre.left = null;
        Node cur = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            pre.right = cur;
            cur.left = pre;
            pre = cur;
        }
        pre.right = null;
        return head;
    }

    private static void inOrderToQueue(Node head, Queue<Node> queue) {
        if (head == null) {
            return;
        }
        inOrderToQueue(head.left, queue);
        queue.offer(head);
        inOrderToQueue(head.right, queue);
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
}
