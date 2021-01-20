package org.example.A_T.T01.class_06;

import org.example.A_T.common.Node;

/**
 * @Author: Derek
 * @DateTime: 2020/11/26 16:58
 * @Description: Morris遍历
 */
public class T_001 {

    public static void main(String[] args) {
        Node node = new Node(5);
        node.left = new Node(4);
        node.right = new Node(3);

        morrisPos(node);
    }

    public static void process(Node head){
        if (head == null)   return;
        Node cur = head;
        Node mostRight = null;
        while (cur != null){

            //cur有没有左树
            mostRight = cur.left;
            if (mostRight != null){//有左树的情况下
                //找到cur左树上真实的最右节点
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                //从while出来 mostRight是cur左树的最右节点
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {//mostRight != null  -> mostRight.right == cur
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }

    public static void morrisIn(Node head){
        if (head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){

            //cur有没有左树
            mostRight = cur.left;
            if (mostRight != null){//有左树的情况下
                //找到cur左树上真实的最右节点
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                //从while出来 mostRight是cur左树的最右节点
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {//mostRight != null  -> mostRight.right == cur
                    mostRight.right = null;
                }
            }
            System.out.print(cur.value+" ");
            cur = cur.right;
        }
    }

    public static void morrisPre(Node head){
        if (head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){

            //cur有没有左树
            mostRight = cur.left;
            if (mostRight != null){//有左树的情况下
                //找到cur左树上真实的最右节点
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                //从while出来 mostRight是cur左树的最右节点
                if (mostRight.right == null){
                    mostRight.right = cur;
                    System.out.print(cur.value+" ");
                    cur = cur.left;
                    continue;
                }else {//mostRight != null  -> mostRight.right == cur
                    mostRight.right = null;
                }
            }else {
                System.out.print(cur.value+" ");
            }
            cur = cur.right;
        }
    }

    public static void morrisPos(Node head){
        if (head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){

            //cur有没有左树
            mostRight = cur.left;
            if (mostRight != null){//有左树的情况下
                //找到cur左树上真实的最右节点
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                //从while出来 mostRight是cur左树的最右节点
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {//mostRight != null  -> mostRight.right == cur
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(head);
        System.out.println();
    }

    private static void printEdge(Node head) {
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null){
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    private static Node reverseEdge(Node cur) {
        Node pre = null;
        Node next = null;
        while (cur != null) {
            next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    //判断是不是搜索二叉树  (中序遍历递增)
    public static boolean isBST(Node head){
        if (head == null){
            return true;
        }
        Node cur = head;
        Node mostRight = null;

        Integer pre = null;
        while (cur != null){

            //cur有没有左树
            mostRight = cur.left;
            if (mostRight != null){//有左树的情况下
                //找到cur左树上真实的最右节点
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                //从while出来 mostRight是cur左树的最右节点
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {//mostRight != null  -> mostRight.right == cur
                    mostRight.right = null;
                }
            }
            if (pre != null && pre >= cur.value){
                return false;
            }
            pre = cur.value;
            cur = cur.right;
        }
        return true;
    }


}
