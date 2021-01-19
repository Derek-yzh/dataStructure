package org.example.A_T.Top_interview.T5;

import java.util.TreeMap;

/**
 * @Author: Derek
 * @DateTime: 2020/12/21 8:42
 * @Description:
 *
 * 给你一个字符串类型的数组arr，譬如:
 * String[] arr = { "b\st", "d\", "a\d\e", "a\b\c" };
 * 把这些路径中蕴含的目录结构给打印出来，子目录直接列在父目录下面，并比父目录向右进两格，就像这样:
 * a
 *   b
 *     c
 *   d
 *     e
 * b
 *   cst
 * d
 * 同一级的需要按字母顺序排列不能乱。
 *
 */
public class T_001 {

    public static void main(String[] args) {
        String[] strings = {"b\\st", "d\\", "a\\d\\e", "a\\b\\c"};
        print(strings);
    }

    public static void print(String[] folderPaths){
        if (folderPaths == null || folderPaths.length < 1){
            return;
        }
        Node head = generateFolderTree(folderPaths);
        printProcess(head,0);
    }

    private static void printProcess(Node node, int level) {
        if (level != 0){
            System.out.println(get4nSpace(level) + node.path);
        }
        for (Node next : node.nextMap.values()) {
            printProcess(next,level+1);
        }
    }

    private static String get4nSpace(int level) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < level; i++) {
            res.append("    ");
        }
        return res.toString();
    }

    private static Node generateFolderTree(String[] folderPaths) {
        Node head = new Node("");
        for (String folderPath : folderPaths) {
            String[] paths = folderPath.split("\\\\");
            Node cur = head;
            for (int i = 0; i < paths.length; i++) {
                if (!cur.nextMap.containsKey(paths[i])){
                    cur.nextMap.put(paths[i],new Node(paths[i]));
                }
                cur = cur.nextMap.get(paths[i]);
            }
        }
        return head;
    }

    public static class Node{
        public String path;
        public TreeMap<String,Node> nextMap;
        public Node(String p){
            this.path = p;
            nextMap = new TreeMap<>();
        }
    }
}
