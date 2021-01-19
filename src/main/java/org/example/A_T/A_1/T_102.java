package org.example.A_T.A_1;

import org.example.A_T.common.Node;

/**
 * @Author: Derek
 * @DateTime: 2020/11/30 9:27
 * @Description:
 *      给定一棵树的头节点head，返回这颗二叉树是不是平衡二叉树 (2)
 */
public class T_102 {

    public static void main(String[] args) {

    }

    public static Info function(Node head){
        if (head == null){
            return new Info(false,0);
        }
        Info leftInfo = function(head.left);
        Info rightInfo = function(head.right);

        int height = Math.max(leftInfo.height,rightInfo.height)+1;
        boolean isBalanced = true;
        if (!leftInfo.isBalanced || !rightInfo.isBalanced || Math.abs(leftInfo.height-rightInfo.height) > 1){
            isBalanced = false;
        }
        return new Info(isBalanced,height);
    }

    public static class Info{
        public boolean isBalanced;
        public int height;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

}
