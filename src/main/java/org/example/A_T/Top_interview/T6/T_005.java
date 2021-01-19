package org.example.A_T.Top_interview.T6;

import org.example.A_T.common.Node;

/**
 * @Author: Derek
 * @DateTime: 2020/12/28 15:02
 * @Description: T
 *
 * 求完全二叉树节点的个数
 *
 * 要求时间复杂度低于O(N)
 *
 */
public class T_005 {

    //保证head为头的树是完全二叉树
    public static int function(Node head){
        if (head == null){
            return 0;
        }
        return bs(head,1,mostLeftLevel(head,1));
    }

    //node在第level层，h是总深度(永远不变，全局变量)
    //以node为头的完全二叉树，节点个数是多少
    private static int bs(Node node, int level, int h) {
        if (level == h){
            return 1;
        }
        if (mostLeftLevel(node.right,level+1) == h){
            return (1 << (h - level)) + bs(node.right,level+1,h);
        }else {
            return (1 << (h - level - 1)) + bs(node.left,level+1,h);
        }
    }

    private static int mostLeftLevel(Node node, int level) {
        while (node != null){
            level++;
            node = node.left;
        }
        return level-1;
    }

}
