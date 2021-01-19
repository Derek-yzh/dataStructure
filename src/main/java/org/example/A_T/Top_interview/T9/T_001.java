package org.example.A_T.Top_interview.T9;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: Derek
 * @DateTime: 2021/1/12 20:33
 * @Description:
 *
 * 给定两个有序数组arr1和arr2，再给定一个正数K
 *
 * 求两个数累加和最大的前K个，两个数必须分别来自arr1和arr2
 *
 */
public class T_001 {

    public static void main(String[] args) {


    }

    public static class Node{
        public int index1;
        public int index2;
        public int sum;

        public Node(int index1, int index2, int sum) {
            this.index1 = index1;
            this.index2 = index2;
            this.sum = sum;
        }
    }

    public static int[] function(int[] arr1, int[] arr2, int topK){
        if (arr1 == null || arr2 == null || topK < 1){
            return null;
        }
        topK = Math.min(topK, arr1.length*arr2.length);

        int[] res = new int[topK];
        int resIndex = 0;
        PriorityQueue<Node> maxHeap = new PriorityQueue<>((o1, o2) -> o2.sum - o1.sum);

        boolean[][] set = new boolean[arr1.length][arr2.length];
        int i1 = arr1.length-1;
        int i2 = arr2.length-1;

        maxHeap.add(new Node(i1,i2,arr1[i1] + arr2[i2]));
        set[i1][i2] = true;
        while (resIndex != topK){
            Node curNode = maxHeap.poll();
            res[resIndex++] = curNode.sum;
            i1 = curNode.index1;
            i2 = curNode.index2;
            if (i1 - 1 >= 0 && !set[i1-1][i2]){
                set[i1-1][i2] = true;
                maxHeap.add(new Node(i1-1,i2,arr1[i1-1]+arr2[i2]));
            }
            if (i2 - 1 >= 0 && !set[i1][i2-1]){
                set[i1][i2-1] = true;
                maxHeap.add(new Node(i1,i2-1,arr1[i1]+arr2[i2-1]));
            }
        }
        return res;
    }

}
