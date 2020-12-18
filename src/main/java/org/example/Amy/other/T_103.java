package org.example.Amy.other;

import org.example.A_T.common.Node;

/**
 * @Author: Derek
 * @DateTime: 2020/11/30 9:38
 * @Description:
 *      给定一课二叉树的头节点head，任何两个节点之间都存在距离，返回整颗二叉树的最大距离
 */
public class T_103 {


    public static Info function(Node head){
        if (head == null){
            return new Info(0,0);
        }
        Info leftInfo = function(head.left);
        Info rightInfo = function(head.right);

        int height = Math.max(leftInfo.height,rightInfo.height)+1;
        int maxDistance = Math.max(
                leftInfo.height+1+rightInfo.height,
                Math.max(leftInfo.maxDistance, rightInfo.maxDistance)
        );
        return new Info(maxDistance,height);

    }

    public static class Info{
        public int maxDistance;
        public int height;

        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

}
