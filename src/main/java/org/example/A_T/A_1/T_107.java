package org.example.A_T.A_1;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Author: Derek
 * @DateTime: 2020/11/30 20:52
 * @Description: 并查集 UnionSet (2)
 *
 * 1)有若干个样本a,b,c,d  假设类型为V
 * 2)在并查集中一开始认为每个样本都在单独的集合里
 * 3)用户可以在任何时候调用如下两个方法：
 *      boolean isSameSet(V a, V b)：查询样本a、样本b是否属于一个集合
 *      void union(V a, V b)：把a、b各自所在的集合合并成一个集合
 * 4)上述两方法代价越低越好
 */
@SuppressWarnings("All")
public class T_107 {

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
