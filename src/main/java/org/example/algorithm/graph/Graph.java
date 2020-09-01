package org.example.algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    private ArrayList<String> vertexList;//存储顶点的集合
    private int[][] edges;//存储图对应的邻接矩阵
    private int numOfEdges;//表示边的数目
    private boolean[] isVisited;

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        String[] vertex = {"A","B","C","D","E"};
        for (String s : vertex) {
            graph.insertVertex(s);
        }
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,4,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,2,1);

        graph.show();
        /*System.out.println("深度优先遍历：");
        graph.dfs();*/
        System.out.println("广度优先遍历：");
        graph.bfs();
    }

    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    //深度优先遍历
    private void dfs(boolean[] isVisited, int i){
        System.out.print(getValueByIndex(i)+"->");
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while (w != -1){
            if (!isVisited[w]){
                dfs(isVisited,w);
            }
            w = getNextNeighbor(i,w);
        }
    }
    public void dfs(){
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    //广度优先遍历
    private void bfs(boolean[] isVisited, int i){
        int u;
        int w;
        //队列，记录节点访问顺序
        LinkedList<Integer> queue = new LinkedList<>();
        System.out.print(getValueByIndex(i)+"->");
        isVisited[i] = true;
        queue.addLast(i);
        while (!queue.isEmpty()){
            u = queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1){
                //是否访问过
                if (!isVisited[w]){
                    System.out.print(getValueByIndex(w)+"->");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u,w);
            }
        }
    }
    public void bfs(){
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    //根据前一个邻接节点的下标获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2){
        for (int i = v2+1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0){
                return i;
            }
        }
        return -1;
    }

    //得到第一个邻接节点的下标
    public int getFirstNeighbor(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0){
                return i;
            }
        }
        return -1;
    }

    public int getNumOfVertex(){
        return vertexList.size();
    }

    public int getNumOfEdges(){
        return numOfEdges;
    }

    //返回节点对应的数据
    public String getValueByIndex(int index){
        return vertexList.get(index);
    }

    public int getWeight(int v1, int v2){
        return edges[v1][v2];
    }

    public void show(){
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    //插入节点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * @param v1 点1的下标  "A"->0 "B"->1
     * @param v2 点2的下标
     * @param weight 权值
     */
    public void insertEdge(int v1, int v2, int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

}
