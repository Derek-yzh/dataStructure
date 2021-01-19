package org.example.A_T.Top_interview.T7;

import java.util.*;

/**
 * @Author: Derek
 * @DateTime: 2020/12/29 15:24
 * @Description:
 *
 * 给定两个字符串，记为start和end，再给定一个字符串列表list，list中一定包含to. list中没有重复字符串，所有的字符串都是小写的。
 * 规定: start每次只能改变一个字符，最终的目标是彻底变成end，但是每次变成的新字符串必须在list 中存在。
 * 请返回所有最短的变换路径。
 * 【举例】
 * start="abc",end="cab",list={"cab","acc","cbc","ccc","cac","cbb","aab","abb"}
 * 转换路径的方法有很多种，但所有最短的转换路径如下:
 * abc -> abb -> aab -> cab
 * abc -> abb -> cbb -> cab
 * abc -> cbc -> cac -> cab
 * abc -> cbc -> cbb -> cab
 *
 */
@SuppressWarnings("all")
public class T_002 {

    public static List<List<String>> findMinPaths(
            String start,
            String end,
            List<String> list){
        list.add(start);
        // 获取所有邻居表
        HashMap<String,ArrayList<String>> nexts = grtNexts(list);

        HashMap<String,Integer> distances = getDistances(start,nexts);

        LinkedList<String> pathList = new LinkedList<>();
        ArrayList<List<String>> res = new ArrayList<>();
        getShortestPaths(start,end,nexts,distances,pathList,res);
        return res;
    }

    /**
     * 深度遍历
     * @param cur 来到了哪里：start
     * @param end 目的地：to
     * @param nexts 能到达的地方
     * @param distances 最短距离表
     * @param pathList 沿途经过的路径
     * @param res 答案
     */
    private static void getShortestPaths(String cur, String end, HashMap<String, ArrayList<String>> nexts, HashMap<String, Integer> distances, LinkedList<String> pathList, ArrayList<List<String>> res) {
        pathList.add(cur);
        if (end.equals(cur)){
            res.add(new LinkedList<String>(pathList));
        }else {
            for (String next : nexts.get(cur)) {
                if (distances.get(next) == distances.get(cur) + 1) {
                    getShortestPaths(next, end, nexts, distances, pathList, res);
                }
            }
        }
        pathList.pollLast();
    }

    // 宽度遍历
    private static HashMap<String, Integer> getDistances(String start, HashMap<String, ArrayList<String>> nexts) {
        HashMap<String, Integer> distances = new HashMap<>();
        distances.put(start,0);
        LinkedList<String> queue = new LinkedList<>();
        queue.addLast(start);
        HashSet<String> set = new HashSet<>();
        set.add(start);
        while (!queue.isEmpty()){
            String cur = queue.poll();
            for (String next : nexts.get(cur)) {
                if (!set.contains(next)){
                    distances.put(next,distances.get(cur)+1);
                    queue.addLast(next);
                    set.add(next);
                }
            }
        }
        return distances;
    }

    private static HashMap<String, ArrayList<String>> grtNexts(List<String> words) {
        HashSet<String> dict = new HashSet<>(words);
        HashMap<String,ArrayList<String>> nexts = new HashMap<>();
        for (int i = 0; i < words.size(); i++) {
            nexts.put(words.get(i),getNext(words.get(i),dict));
        }
        return nexts;
    }

    private static ArrayList<String> getNext(String word, HashSet<String> dict) {
        ArrayList<String> res = new ArrayList<>();
        char[] chs = word.toCharArray();
        for (char cur = 'a'; cur < 'z'; cur++){
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] != cur) {
                    char tmp = chs[i];
                    chs[i] = cur;
                    if (dict.contains(String.valueOf(chs))) {
                        res.add(String.valueOf(chs));
                    }
                    chs[i] = tmp;
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        String start = "abc";
        String end = "cab";
        String[] test = { "abc", "cab", "acc", "cbc", "ccc", "cac", "cbb",
                "aab", "abb" };
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < test.length; i++) {
            list.add(test[i]);
        }
        List<List<String>> res = findMinPaths(start, end, list);
        for (List<String> obj : res) {
            for (String str : obj) {
                System.out.print(str + " -> ");
            }
            System.out.println();
        }

    }

}
