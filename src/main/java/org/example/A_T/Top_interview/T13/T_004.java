package org.example.A_T.Top_interview.T13;

import org.example.A_T.common.TrieNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Derek
 * @DateTime: 2021/3/23 13:08
 * @Description:
 *
 * 给定一个字符类型的二维数组board，和一个字符串组成的列表words。
 * 可以从board任何位置出发，每一步可以走向上、下、左、右，四个方向，
 * 但是一条路径已经走过的位置，不能重复走。
 * 返回words哪些单词可以被走出来。
 * 例子
 * board = [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 * words = ["oath","pea","eat","rain"]
 *
 * 输出：["eat","oath"]
 *
 */
public class T_004 {




    public static int function(
            char[][] board,
            int row, int col,
            LinkedList<Character> path,
            TrieNode cur,
            List<String> res){
        char cha = board[row][col];
        if (cha == 0) return 0; //回头路

        int index = cha - 'a';
        //如果没有路，或者这条路上最终的字符串之前加入过结果里
        if (cur.nexts[index] == null || cur.nexts[index].pass == 0) return 0;

        //没有走回头路并且能登上去
        cur = cur.nexts[index];
        path.addLast(cha);//当前位置的字符加入到路径里去
        int fix = 0;//从row和col位置出发，后续一共搞定了多少答案

        if (cur.end > 0){
            res.add(generatePath(path));
            cur.end--;
            fix++;
        }

        board[row][col] = 0;
        if (row > 0) fix += function(board,row-1,col,path,cur,res);
        if (row+1 < board.length) fix += function(board,row+1,col,path,cur,res);
        if (row > 0) fix += function(board,row,col-1,path,cur,res);
        if (row+1 < board[0].length-1) fix += function(board,row,col+1,path,cur,res);
        board[row][col] = cha;
        path.pollLast();
        cur.pass -= fix;
        return fix;
    }

    private static String generatePath(LinkedList<Character> path) {


        return null;
    }

}
