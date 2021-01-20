package org.example.A_T.T01.class_01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: Derek
 * @DateTime: 2020/11/20 14:54
 * @Description: 单调栈 (2)
 *      给定一个数组arr
 *      返回数组中每个值 左边离它最近比他小的数和右边离它最近比他小的数
 *
 *      例如 arr = [1,2,1]
 *      返回：0 : 	-1	-1
 *           1 : 	0	2
 *           2 : 	-1	-1
 */
public class T_003 {

    public static int[][] function(int[] arr){
        if (arr == null || arr.length < 1) return null;
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();//下小上大
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]){
                List<Integer> pop = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
                for (Integer p : pop) {
                    res[p][0] = leftLessIndex;
                    res[p][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) stack.peek().add(i);
            else {
                LinkedList<Integer> list = new LinkedList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()){
            List<Integer> pop = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
            for (Integer p : pop) {
                res[p][0] = leftLessIndex;
                res[p][1] = -1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,1};
        int[][] res = function(arr);
        for (int i = 0; i < res.length; i++) {
            System.out.print(i+" : ");
            for (int j = 0; j < res[i].length; j++) {
                System.out.print("\t"+res[i][j]);
            }
            System.out.println();
        }
    }

    public static int[][] getNearLess(int[] arr) {

        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]){
                List<Integer> pop = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
                for (Integer p : pop) {
                    res[p][0] = leftLessIndex;
                    res[p][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]){
                List<Integer> peek = stack.peek();
                peek.add(i);
            }else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }

        while (!stack.isEmpty()){
            List<Integer> pop = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
            for (Integer p : pop) {
                res[p][0] = leftLessIndex;
                res[p][1] = -1;
            }

        }

        return res;
    }

}
