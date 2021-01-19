package org.example.A_T.A_1;

import java.util.PriorityQueue;

/**
 * @Author: Derek
 * @DateTime: 2020/11/30 16:33
 * @Description: 贪心（最常见用排序或者堆做） (2)
 *
 *      一根金条切成两半，是需要划分和长度数值一样的铜板的
 *      一群人想整分整块金条，怎么分最省铜板？
 *      输入一个数组，返回分割的最小代价。
 *      如：{10，20，30}，代表一共三个人，整块金条长69，金条要分成10，20，30三个部分
 */
public class T_106 {

    public static void main(String[] args) {
        int[] arr = {10,20,30};
        System.out.println(function(arr));
    }

    public static int function(int[] arr){
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pQ.add(arr[i]);
        }
        int sum = 0;
        int cur = 0;
        while (pQ.size() > 1){
            cur = pQ.poll()+pQ.poll();
            sum += cur;
            pQ.add(cur);
        }
        return sum;
    }

}
