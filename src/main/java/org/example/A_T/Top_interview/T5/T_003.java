package org.example.A_T.Top_interview.T5;

/**
 * @Author: Derek
 * @DateTime: 2020/12/21 10:10
 * @Description:
 *
 * 已知一棵二叉树中没有重复节点，并且给定了这棵树的中序遍历数组和先序遍历 数组，返回后序遍历数组。
 * 比如给定:
 * int[] pre = { 1, 2, 4, 5, 3, 6, 7 };
 * int[] in = { 4, 2, 5, 1, 6, 3, 7 }; 返回:
 * {4,5,2,6,7,3,1}
 *
 */
public class T_003 {

    public static void main(String[] args) {
        int[] pre = { 1, 2, 4, 5, 3, 6, 7 };
        int[] in = { 4, 2, 5, 1, 6, 3, 7 };
        int[] pos = function(pre, in);
        for (int i : pos) {
            System.out.print(i+" ");
        }
    }

    private static int[] function(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length){
            return null;
        }
        int N = pre.length;
        int[] pos = new int[N];
        process(pre,0,N-1,in,0,N-1,pos,0,N-1);
        return pos;
    }

    public static void process(
            int[] pre, int L1, int R1,
            int[] in, int L2, int R2,
            int[] pos, int L3, int R3){
        if (L1 > R1){
            return;
        }
        if (L1 == R1){
            pos[L3] = pre[L1];
            return;
        }
        pos[R3] = pre[L1];

        int mid = L2;
        for (;  mid<= R2; mid++) {
            if (in[mid] == pre[L1]){
                break;
            }
        }

        int leftSize = mid - L2;
        //左
        process(pre,L1+1,L1+leftSize,in,L2,mid-1,pos,L3,L3+leftSize-1);
        //右
        process(pre,L1+leftSize+1,R1,in,mid+1,R2,pos,L3+leftSize,R3-1);
    }

}