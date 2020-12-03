package org.example.Amy.other;

import org.example.A_T.common.Node;

/**
 * @Author: Derek
 * @DateTime: 2020/11/30 9:51
 * @Description:
 *      给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的节点数
 */
public class T_104 {

    public static void main(String[] args) {

        Node head = new Node(7);
        head.left = new Node(5);
        head.right = new Node(8);


        Info info = process(head);
        System.out.println(info);

    }

    public static Info process(Node head){
        if (head == null){
            return null;
        }
        return function(head);
    }

    public static Info function(Node head){
        if (head == null){
            return null;
        }
        Info leftInfo = function(head.left);
        Info rightInfo = function(head.right);


        int max = head.value;
        int min = head.value;
        if (leftInfo != null){
            min = Math.min(min,leftInfo.min);
            max = Math.max(max,leftInfo.max);
        }
        if (rightInfo != null){
            min = Math.min(min,rightInfo.min);
            max = Math.max(max,rightInfo.max);
        }

        int size = 0;
        if (leftInfo != null){
            size = leftInfo.size;
        }
        if (rightInfo != null){
            size = Math.max(size,rightInfo.size);
        }
        boolean isAllBST = false;

        if (
                (leftInfo == null || leftInfo.isAllBST)
                &&
                (rightInfo == null || rightInfo.isAllBST)
                &&
                (leftInfo == null || leftInfo.max < head.value)
                &&
                (rightInfo == null || rightInfo.min > head.value)
        ){
            size = 1 + (leftInfo == null ?0:leftInfo.size) + (rightInfo == null?0:rightInfo.size);
            isAllBST = true;
        }
        return new Info(max,min,isAllBST,size);
    }

    public static class Info{
        public int max;
        public int min;
        public boolean isAllBST;
        public int size;

        public Info(int max, int min, boolean isAllBST, int size) {
            this.max = max;
            this.min = min;
            this.isAllBST = isAllBST;
            this.size = size;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "max=" + max +
                    ", min=" + min +
                    ", isAllBST=" + isAllBST +
                    ", size=" + size +
                    '}';
        }
    }
}
