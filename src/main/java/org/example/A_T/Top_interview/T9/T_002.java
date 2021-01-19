package org.example.A_T.Top_interview.T9;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Derek
 * @DateTime: 2021/1/12 21:20
 * @Description:
 *
 * 给定一个正数数组arr，返回该数组能不能分成4个部分，并且每个部分的累加和相等，切分位置的数不要。
 * 例如:
 * arr=[3, 2, 4, 1, 4, 9, 5, 10, 1, 2, 2] 返回true
 * 三个切割点下标为2, 5, 7. 切出的四个子数组为[3,2], [1,4], [5], [1,2,2]，
 * 累加和都是5
 *
 */
public class T_002 {

    public static boolean function(int[] arr){
        if (arr == null || arr.length < 7){
            return false;
        }
        // key 某一个累加和， value出现的位置
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            map.put(sum, i);
            sum += arr[i];
        }

        int lsum = arr[0]; // 第一刀左侧的累加和
        for (int s1 = 1; s1 < arr.length - 5; s1++) { // s1是第一刀的位置
            int checkSum = lsum * 2 + arr[s1]; // 100 x 100   100*2 + x
            if (map.containsKey(checkSum)) {
                int s2 = map.get(checkSum); // j -> y
                checkSum += lsum + arr[s2];
                if (map.containsKey(checkSum)) { // 100 * 3 + x + y
                    int s3 = map.get(checkSum); // k -> z
                    if (checkSum + arr[s3] + lsum == sum) {
                        return true;
                    }
                }
            }
            lsum += arr[s1];
        }
        return false;

    }

}
