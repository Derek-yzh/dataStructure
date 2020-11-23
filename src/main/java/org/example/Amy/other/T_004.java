package org.example.Amy.other;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 * @Author: Derek
 * @DateTime: 2020/9/26 10:13
 * @Description: 生成随机位数数字
 */
public class T_004 {

    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            set.add(getRandomNickname(10));
        }
        System.out.println(set);
        Iterator<String> iterator = set.iterator();
        if (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
            set.remove(next);
        }
        System.out.println(set);

    }

    public static String getRandomNickname(int length) {
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val.append(String.valueOf(random.nextInt(10)));
        }
        return val.toString();
    }

}
