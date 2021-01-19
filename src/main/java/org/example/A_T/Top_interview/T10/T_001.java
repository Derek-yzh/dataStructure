package org.example.A_T.Top_interview.T10;

import org.example.A_T.A_1.T_107;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * @Author: Derek
 * @DateTime: 2021/1/14 19:31
 * @Description:
 *
 * 一个数组中，如果两个数的最小公共因子大于1，则认为这两个数之间有通路
 *
 * 返回数组中，有多少个独立的域
 *
 */
public class T_001 {

    public static int function(int[] arr){
        UnionFindSet set = new UnionFindSet(arr.length);
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (gcd(arr[i],arr[j]) != 1){
                    set.union(i,j);
                }
            }
        }
        return set.maxSize();
    }

    public static int gcd(int m, int n){
        return  n == 0 ? m : gcd(n,m % n) ;
    }

    public static class UnionFindSet {
        public HashMap<Integer, Integer> fatherMap; // key 的父亲 value
        // key是代表点的时候，才有记录，value是所在集合一共有多少点
        public HashMap<Integer, Integer> sizeMap;

        public UnionFindSet(int size) {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (int i = 0; i < size; i++) {
                fatherMap.put(i, i);
                sizeMap.put(i, 1);
            }
        }

        public int size() {
            return sizeMap.size();
        }

        public int maxSize() {
            int ans = 0;
            for (int size : sizeMap.values()) {
                ans = Math.max(ans, size);
            }
            return ans;
        }

        private int findHead(int element) {
            Stack<Integer> path = new Stack<>();
            while (element != fatherMap.get(element)) {
                path.push(element);
                element = fatherMap.get(element);
            }
            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), element);
            }
            return element;
        }

        public void union(int a, int b) {
            int aF = findHead(a);
            int bF = findHead(b);
            if (aF != bF) {
                int big = sizeMap.get(aF) >= sizeMap.get(bF) ? aF : bF;
                int small = big == aF ? bF : aF;
                fatherMap.put(small, big);
                sizeMap.put(big, sizeMap.get(aF) + sizeMap.get(bF));
                sizeMap.remove(small);
            }

        }
    }

}
