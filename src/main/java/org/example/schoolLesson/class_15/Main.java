package org.example.schoolLesson.class_15;

import java.util.Stack;

/**
 * @Author: Derek
 * @DateTime: 2020/12/6 18:34
 * @Description: 15数码
 */
public class Main {
    public static Board beginBoard = new Board(Init.BEGINARR);
    public static Board endBoard = new Board(Init.ENDARR);

    public static void main(String[] args) {

        OpenTable openTable = new OpenTable();
        openTable.tbArr.add(beginBoard);// 这里需要拷贝逻辑 否则复制的只是指针
        CloseTable closeTable = new CloseTable();

        System.out.print(openTable);
        Board curBoard = null;

        int count = 0;

        while (openTable.tbArr.size() != 0) {
            //System.out.println("第" + ++count + "次拓展");
            openTable.sortItSelf();
            curBoard = openTable.getMinBoard();
            closeTable.tbArr.add(curBoard);

            // 是否为end
            if (curBoard.equals(endBoard)) {
                System.out.println("目标:" + curBoard);
                System.out.println("====================== 移动策略: 共"+curBoard.fn+"步 ==============");
                Stack<Board> stack = new Stack<>();
                stack.add(curBoard);
                while (curBoard.parentBoard != null) {
                    //System.out.println("上一步：");
                    stack.add(curBoard.parentBoard);
                    //System.out.println(curBoard.parentBoard);
                    curBoard = curBoard.parentBoard;
                }
                System.out.print("初始：");
                System.out.println(stack.pop());
                int i = 1;
                while (!stack.isEmpty()){
                    System.out.print("第"+(i++)+"步：");
                    System.out.println(stack.pop());
                }
                break;
            }
            // 4方向拓展
            for (int s = 0; s < 4; s++) {
                Board newBoard = null;
                if (s == 0) {
                    if (curBoard.canDown()) {
                        newBoard = curBoard.goDown();
                    } else {
                        continue;
                    }
                } else if (s == 1) {
                    if (curBoard.canUp()) {
                        newBoard = curBoard.goUp();
                    } else {
                        continue;
                    }
                } else if (s == 2) {
                    if (curBoard.canRight()) {
                        newBoard = curBoard.goRight();
                    } else {
                        continue;
                    }
                } else if (s == 3) {
                    if (curBoard.canLeft()) {
                        newBoard = curBoard.goLeft();
                    } else {
                        continue;
                    }
                }

                if (openTable.hasBoard(newBoard)) {// 新节点在open表中 比较已有fn和新节点fn的大小
                    Board oldBoard = openTable.getBoardByArr(newBoard.arr);
                    if (newBoard.fn < oldBoard.fn) {// 新的比旧的fn小
                        // 更新指针
                        newBoard.childBoards = oldBoard.childBoards;
                        for (int i = 0; i < newBoard.childBoards.size(); i++) {
                            newBoard.childBoards.get(i).parentBoard = newBoard;
                        }
                        // 删旧的 增新的
                        openTable.tbArr.remove(openTable.getIndex(oldBoard));
                        openTable.tbArr.add(newBoard);
                        //System.out.println("openTable:update");
                    }
                } else if (closeTable.hasBoard(newBoard)) {// 新节点在close表中
                    Board oldBoard = closeTable.getBoardByArr(newBoard.arr);
                    if (newBoard.fn < oldBoard.fn) {
                        newBoard.childBoards = oldBoard.childBoards;
                        for (int i = 0; i < newBoard.childBoards.size(); i++) {
                            newBoard.childBoards.get(i).parentBoard = newBoard;
                        }
                        closeTable.tbArr.remove(closeTable.getIndex(oldBoard));
//						closeTable.tbArr.add(newBoard);
                        openTable.tbArr.add(newBoard);// 重新放回open表中
                        //System.out.println("openTable:update");
                    }
                } else {// 两表都不在
                    openTable.tbArr.add(newBoard);
                    //System.out.println("openTable:add");
                }
            }
            openTable.tbArr.remove(openTable.getIndex(curBoard));
        }
    }
}

