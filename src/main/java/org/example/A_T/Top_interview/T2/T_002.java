package org.example.A_T.Top_interview.T2;

/**
 * @Author: Derek
 * @DateTime: 2020/12/8 19:09
 * @Description:
 *
 * 给定一个二叉树的头节点head，路径的规定有以下三种不同的规定：
 *
 * 1）路径必须是头节点出发，到叶节点为止，返回最大路径和
 *
 * 2）路径可以从任何节点出发，但必须往下走到达任何节点，返回最大路径和
 *
 * 3）路径可以从任何节点出发，到任何节点，返回最大路径和
 *
 */
public class T_002 {
    private int allTreeMaxSum;

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int val) {
            value = val;
        }
    }

    public static int maxSum = Integer.MAX_VALUE;
    public static void main(String[] args) {

    }

    public static int maxPath1(Node head){
        maxSum = Integer.MIN_VALUE;
        f1(head,0);
        return maxSum;
    }
    public static void f1(Node x, int pre){
        if (x.left == null && x.right == null){
            maxSum = Math.max(maxSum,pre+x.value);
        }
        if (x.left != null){
            f1(x.left,pre+x.value);
        }
        if (x.right != null){
            f1(x.right,pre+x.value);
        }
    }



    public static int maxPath2(Node head){
        if (head == null){
            return 0;
        }
        return f2(head).allTreeMaxSum;
    }
    static class Info{
        public int allTreeMaxSum;
        public int fromHeadMaxSum;
        public Info(int allTreeMaxSum, int fromHeadMaxSum) {
            this.allTreeMaxSum = allTreeMaxSum;
            this.fromHeadMaxSum = fromHeadMaxSum;
        }
    }
    //1)    1.左树上的整体最大路径和   2.右树上的整体最大路径和
    //2)    1.x自己                  4.x往左走               5.x往右走
    private static Info f2(Node x) {
        if (x == null){
            return null;
        }
        Info leftInfo = f2(x.left);
        Info rightInfo = f2(x.left);

        int p1 = Integer.MIN_VALUE;
        if (leftInfo != null){
            p1 = leftInfo.allTreeMaxSum;
        }
        int p2 = Integer.MIN_VALUE;
        if (leftInfo != null){
            p2 = leftInfo.allTreeMaxSum;
        }
        int p3 = x.value;
        int p4 = Integer.MIN_VALUE;
        if (leftInfo != null){
            p4 = x.value + leftInfo.fromHeadMaxSum;
        }
        int p5 = Integer.MIN_VALUE;
        if (rightInfo != null){
            p5 = x.value + rightInfo.fromHeadMaxSum;
        }

        int allTreeMaxSum = Math.max(p1,Math.max(Math.max(p2,p3),Math.max(p4,p5)));
        int fromHeadMaxSum = Math.max(p3,Math.max(p4,p5));
        return new Info(allTreeMaxSum,fromHeadMaxSum);
    }



    //1)    1.左树上的整体最大路径和   2.右树上的整体最大路径和
    //2)    1.x自己                  4.x往左走               5.x往右走      6.x往左+往右
    private static Info f3(Node x) {
        if (x == null){
            return null;
        }
        Info leftInfo = f3(x.left);
        Info rightInfo = f3(x.left);

        int p1 = Integer.MIN_VALUE;
        if (leftInfo != null){
            p1 = leftInfo.allTreeMaxSum;
        }
        int p2 = Integer.MIN_VALUE;
        if (leftInfo != null){
            p2 = leftInfo.allTreeMaxSum;
        }
        int p3 = x.value;
        int p4 = Integer.MIN_VALUE;
        if (leftInfo != null){
            p4 = x.value + leftInfo.fromHeadMaxSum;
        }
        int p5 = Integer.MIN_VALUE;
        if (rightInfo != null){
            p5 = x.value + rightInfo.fromHeadMaxSum;
        }
        int p6 = Integer.MIN_VALUE;
        if (leftInfo != null && rightInfo != null){
            p6 = x.value + leftInfo.fromHeadMaxSum + rightInfo.fromHeadMaxSum;
        }

        int allTreeMaxSum = Math.max(Math.max(p1,p6),Math.max(Math.max(p3,p3),Math.max(p4,p5)));
        int fromHeadMaxSum = Math.max(p3,Math.max(p4,p5));
        return new Info(allTreeMaxSum,fromHeadMaxSum);
    }


}
