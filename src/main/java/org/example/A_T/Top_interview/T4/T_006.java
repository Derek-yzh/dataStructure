package org.example.A_T.Top_interview.T4;

import java.util.*;

/**
 * @Author: Derek
 * @DateTime: 2020/12/20 12:07
 * @Description:
 *
 * 给定一个由字符串组成的数组String[] strs，给定一个正数K
 *
 * 返回词频最大的前K个字符串，假设结果是唯一的
 *
 */
public class T_006 {

    public static void main(String[] args) {
        String[] arr = { "A", "B", "A", "C", "A", "C", "B", "B", "K" };
        int K = 2;
        function(arr,K);
    }

    public static void function(String[] arr, int k){
        if (arr == null || arr.length < 1){
            return;
        }
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : arr) {
            if (!map.containsKey(s)){
                map.put(s,1);
            }else {
                map.put(s,map.get(s)+1);
            }
        }
        k = Math.min(k,arr.length);
        PriorityQueue<Node> heap = new PriorityQueue<>((o1, o2) -> o2.times-o1.times);

        /*for (Map.Entry<String, Integer> entry : map.entrySet()) {
            heap.add(new Node(entry.getKey(),entry.getValue()));
        }

        for (int i = 0; i < k; i++) {
            Node poll = heap.poll();
            if (poll != null) {
                System.out.println(poll);
            }else {
                return;
            }
        }*/
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Node cur = new Node(entry.getKey(), entry.getValue());
            if (heap.size() < k) {
                heap.add(cur);
            } else {
                if (heap.peek().times < cur.times) {
                    heap.poll();
                }
            }

        }
        while (!heap.isEmpty()) {
            System.out.println(heap.poll().str);
        }
    }

    public static class Node {
        public String str;
        public int times;
        public Node(String s, int t) {
            str = s;
            times = t;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "str='" + str + '\'' +
                    ", times=" + times +
                    '}';
        }
    }

}
