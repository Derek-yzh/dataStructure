package org.example.A_T.T02.class_03;

import java.util.HashMap;

/**
 * @Author: Derek
 * @DateTime: 2020/11/29 14:22
 * @Description: 哈希函数有关的结构和岛问题
 */
public class T_001 {

    public static void main(String[] args) {
        int[] arr = new int[100];//3200位

        int position = 453;
        //提取
        int status = ((arr[453/32] >> (453%32)) & 1);
        System.out.println(status);

        //设置
        arr[453/32] = arr[453/32] | (1 << (453%32));
        status = ((arr[453/32] >> (453%32)) & 1);
        System.out.println(status);

    }


}
