package org.example.others.algorithm.tree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 2020-07-15 10:58:57
 * 哈夫曼树
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node root = createHuffman(arr);
        root.preOrder();
        
    }

    //构建哈夫曼树
    public static Node createHuffman(int[] arr){
        ArrayList<Node> list = new ArrayList<>();
        for (int i : arr) {
            list.add(new Node(i));
        }
        while (list.size() > 1) {
            Collections.sort(list);
            Node left = list.get(0);
            Node right = list.get(1);
            Node parent = new Node(left.getValue() + right.getValue());
            parent.setLeft(left);
            parent.setRight(right);
            list.remove(left);
            list.remove(right);
            list.add(parent);
        }
        return list.get(0);
    }

}

class Node implements Comparable<Node>{
    private int value;
    private Node left;
    private Node right;

    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {

        return this.value - o.value;
    }
}
