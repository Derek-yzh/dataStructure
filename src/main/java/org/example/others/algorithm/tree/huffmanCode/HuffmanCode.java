package org.example.others.algorithm.tree.huffmanCode;

import java.util.*;

/**
 * 2020-07-17 12:38:26
 * 哈夫曼编码
 */
public class HuffmanCode {

    //根据bytes[]创建list
    private static List<Node> getNodes(byte[] bytes ){
        ArrayList<Node> nodes = new ArrayList<>();
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b,1);
            }else {
                counts.put(b,count+1);
            }
        }
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }

    //生成哈夫曼树
    private static Node create(List<Node> nodes){
        while (nodes.size() > 1){
            Collections.sort(nodes);
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(null, left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        Map<Byte, String> hCodes = getCodes(contentBytes);
        System.out.println(hCodes);
        byte[] zip = zip(contentBytes, hCodes);
        System.out.println(Arrays.toString(zip));

        byte[] decode = decode(huffmanCodes, zip);
        System.out.println(new String(decode));
    }

    /**
     * 解码
     * @param huffmanCodes
     * @param huffmanBytes
     * @return
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = (i == huffmanBytes.length-1);
            builder.append(byteToBitString(!flag,huffmanBytes[i]));
        }

        HashMap<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(),entry.getKey());
        }

        ArrayList<Byte> list = new ArrayList<>();
        for (int i = 0; i < builder.length();) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag){
                String key = builder.substring(i,i+count);
                b = map.get(key);
                if (b == null) {
                    count++;
                }
                else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    /**
     * 将一个byte转成一个二进制字符串
     * @param flag 是否补高位 true为是 最后一个字节不用补高位
     * @param b byte
     * @return b对应二进制字符串(补码返回)
     */
    private static String byteToBitString(boolean flag, byte b){
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        String string = Integer.toBinaryString(temp);//返回的是temp对应的二进制的补码
        if (flag){
            return string.substring(string.length()-8);
        }
        return string;
    }

    public static Map<Byte,String> getCodes(byte[] contentBytes){
        //System.out.println(contentBytes.length);//40
        Node root = create(getNodes(contentBytes));
        //preOrder(root);
        getCodes(root,"",builder);
        //System.out.println(huffmanCodes);
        return huffmanCodes;
    }

    private static byte[] zip(byte[] bytes, Map<Byte,String> huffmanCodes){
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        builder = stringBuilder;
        //System.out.println(stringBuilder);
        int len = (stringBuilder.length() + 7) /8;
        byte[] by = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String sByte;
            if (i+8 > stringBuilder.length()){
                sByte = stringBuilder.substring(i);
            }else {
                sByte = stringBuilder.substring(i, i + 8);
            }
            by[index] = (byte) Integer.parseInt(sByte,2);
            index++;
        }
        return by;
    }

    //创建哈夫曼编码表
    private static StringBuilder builder = new StringBuilder();
    private static Map<Byte,String> huffmanCodes = new HashMap<Byte, String>();
    /**
     * 将传入的node节点的所有叶子节点的哈夫曼编码得到，并放入huffmanCodes集合中
     * @param node 传入节点
     * @param code 路径:左子节点0.右子节点1
     * @param builder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder builder){
        StringBuilder stringBuilder = new StringBuilder(builder);
        stringBuilder.append(code);
        if (node != null) {
            if (node.data == null){
                getCodes(node.left,"0",stringBuilder);
                getCodes(node.right,"1",stringBuilder);
            }else {
                huffmanCodes.put(node.data,stringBuilder.toString());
            }
        }
    }

    private static void preOrder(Node root){
        if (root == null) {
            System.out.println("空哈夫曼树!");
        }else {
            root.preOrder();
        }
    }
}

class Node implements Comparable<Node>{
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(Node node) {
        return this.weight - node.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}
