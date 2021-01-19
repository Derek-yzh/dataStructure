package org.example.A_T.Top_interview.T4;

import lombok.Data;

import java.util.HashMap;

/**
 * @Author: Derek
 * @DateTime: 2020/12/20 12:45
 * @Description:
 *
 * 请实现如下结构：
 * TopRecord{
 *      public TopRecord(int K)  :  构造时事先指定好K的大小，构造后就固定不变了
 *      public  void add(String str)  :   向该结构中加入一个字符串，可以重复加入
 *      public  List<String> top() : 返回之前加入的所有字符串中，词频最大的K个
 * }
 * 要求：
 * add方法，复杂度O(log K);
 * top方法，复杂度O(K)
 *
 */
public class T_007 {

    public static void main(String[] args) {
        TopKRecord record = new TopKRecord(2);
        record.add("zuo");
        record.printTopK();
        record.add("cheng");
        record.add("cheng");
        record.printTopK();
        record.add("Yun");
        record.add("Yun");
        record.printTopK();
    }

    @Data
    static class TopKRecord{
        private Node[] heap;
        private int heapSize;
        // string -> Node(times) 词频表
        private HashMap<String, Node> strNodeMap;
        // 索引表
        private HashMap<Node, Integer> nodeIndexMap;

        public TopKRecord(int K) {
            heap = new Node[K];
            heapSize = 0;
            strNodeMap = new HashMap<String, Node>();
            nodeIndexMap = new HashMap<Node, Integer>();
        }

        public void add(String s) {
            Node curNode = null;
            int preIndex = -1; // str之前在堆上的位置

            if (strNodeMap.containsKey(s)){
                curNode = strNodeMap.get(s);
                curNode.times++;
                preIndex = nodeIndexMap.get(curNode);
            }else {
                curNode = new Node(s, 1);
                strNodeMap.put(s,curNode);
                nodeIndexMap.put(curNode, -1);
            }

            if (preIndex == -1){ //不在堆上
                if (heapSize == heap.length){ //堆满
                    if (heap[0].times < curNode.times){
                        nodeIndexMap.put(heap[0],-1);
                        nodeIndexMap.put(curNode,0);
                        heap[0] = curNode;
                        heapify(0,heapSize);
                    }
                }else { //堆不满
                    nodeIndexMap.put(curNode,heapSize);
                    heap[heapSize] = curNode;
                    heapInsert(heapSize++);
                }
            }else { //在堆上
                heapify(preIndex,heapSize);
            }
        }

        private void heapInsert(int index) {
            while (index != 0) {
                int parent = (index - 1) / 2;
                if (heap[index].times < heap[parent].times) {
                    swap(parent, index);
                    index = parent;
                } else {
                    break;
                }
            }
        }

        private void heapify(int index, int heapSize) {
            int l = index * 2 + 1;
            int r = index * 2 + 2;
            int smallest = index;
            while (l < heapSize) {
                if (heap[l].times < heap[index].times) {
                    smallest = l;
                }
                if (r < heapSize && heap[r].times < heap[smallest].times) {
                    smallest = r;
                }
                if (smallest != index) {
                    swap(smallest, index);
                } else {
                    break;
                }
                index = smallest;
                l = index * 2 + 1;
                r = index * 2 + 2;
            }
        }

        private void swap(int index1, int index2) {
            nodeIndexMap.put(heap[index1], index2);
            nodeIndexMap.put(heap[index2], index1);
            Node tmp = heap[index1];
            heap[index1] = heap[index2];
            heap[index2] = tmp;
        }

        public void printTopK() {
            System.out.println("TOP: ");
            for (int i = 0; i != heap.length; i++) {
                if (heap[i] == null) {
                    break;
                }
                System.out.print("Str: " + heap[i].str);
                System.out.println(" Times: " + heap[i].times);
            }
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
