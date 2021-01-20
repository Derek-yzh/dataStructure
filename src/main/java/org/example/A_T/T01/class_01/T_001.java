package org.example.A_T.T01.class_01;

import java.util.LinkedList;

/**
 * @Author: Derek
 * @DateTime: 2020/11/18 19:07
 * @Description: 滑动窗口 双端链表 (2)
 *      假设一个固定大小为W的窗口，依次划过arr，返回每一次滑出状况的最大值
 *      例如： arr = [4,3,5,4,3,3,6,7],W=3
 *            返回：[5,5,5,4,6,7]
 */
public class T_001 {


    public static int[] function(int[] arr, int w){
        if (arr == null || arr.length == 0) return null;
        int[] res = new int[arr.length - w + 1];
        LinkedList<Integer> list = new LinkedList<>(); //list存放数组下标 此处要使得list左边头为最大值
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!list.isEmpty() && arr[i] >= arr[list.peekLast()]) list.pollLast();
            list.addLast(i);
            if (i - list.peekFirst() == w) list.pollFirst();
            if (i >= w-1) res[index++] = arr[list.peekFirst()];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {4,3,5,4,3,3,6,7};
        int W=3;
        int[] res = function(arr, W);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i]+" ");
        }
    }

    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w){
            return null;
        }

        LinkedList<Integer> list = new LinkedList<>();//双端队列存放最大值下标
        int[] res = new int[arr.length+1-w];
        int index = 0;

        for (int i = 0; i < arr.length; i++) {
            while (!list.isEmpty() && arr[i] >= arr[list.peekLast()]){
                list.pollLast();
            }
            list.addLast(i);

            //如果窗口没有形成w的长度之前。不弹出数字
            if (list.peekFirst() == i - w){
                list.pollFirst();
            }

            if (i-w >= -1){
                res[index++] = arr[list.peekFirst()];
            }

        }
        return res;
    }



}
