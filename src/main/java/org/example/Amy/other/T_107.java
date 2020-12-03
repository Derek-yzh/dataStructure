package org.example.Amy.other;

import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @Author: Derek
 * @DateTime: 2020/11/30 20:52
 * @Description: 并查集 UnionSet
 */
public class T_107 {

    public static void main(String[] args) {

    }

    public static class UnionSet<V>{
        public HashMap<V,Node<V>> nodes;
        public HashMap<Node<V>,Node<V>> parents;
        public HashMap<Node<V>,Integer> sizeMap;//代表点数量记录

        public UnionSet(List<V> values){
            for (V value : values) {
                Node<V> cur = new Node<>(value);
                nodes.put(value,cur);
                parents.put(cur,cur);
                sizeMap.put(cur,1);
            }
        }

        //进行扁平优化
        public Node<V> findFather(Node<V> cur){
            Stack<Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)){
                path.push(cur);
                cur = parents.get(cur);
            }
            while (!path.isEmpty()){
                parents.put(path.pop(),cur);
            }
            return cur;
        }

        public boolean isSameSet(V a, V b){
            if (!nodes.containsKey(a) || !nodes.containsKey(b)){
                return false;
            }
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a, V b){
            if (!nodes.containsKey(a) || !nodes.containsKey(b)){
                return;
            }
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead){
                Integer aSetSize = sizeMap.get(aHead);
                Integer bSetSize = sizeMap.get(bHead);

                if (aSetSize >= bSetSize){
                    parents.put(bHead,aHead);
                    sizeMap.put(aHead,aSetSize+bSetSize);
                    sizeMap.remove(bHead);
                }else {
                    parents.put(aHead,bHead);
                    sizeMap.put(bHead,aSetSize+bSetSize);
                    sizeMap.remove(aHead);
                }
            }
        }

    }

    public static class Node<V>{
        public V v;
        public Node(V v) {
            this.v = v;
        }
    }

}
