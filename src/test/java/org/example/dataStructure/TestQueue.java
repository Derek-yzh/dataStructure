package org.example.dataStructure;

import org.example.others.algorithm.array.MyArrayRingQueue;
import org.example.others.dataInterface.MyList;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 * 2020-07-02 12:51:23
 * 测试Queue
 */
public class TestQueue {

    @Test
    public void test(){}

    public static void main(String[] args) {
        //MyList<Integer> queue = new MyArrayQueue<>(3);
        MyList<Integer> queue = new MyArrayRingQueue<>(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add ):添加数据");
            System.out.println("g(get ):取出数据");
            System.out.println("h(head):查看队列头");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    System.out.print("当前队列为: ");
                    queue.show();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("输入一个数字：");
                    int val = 0;
                    try {
                        val = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("出错啦！");
                        return;
                    }
                    queue.add(val);
                    break;
                case 'g':
                    try {
                        Object o = queue.get();
                        System.out.println("取出的数据为："+o);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        Object o = queue.first();
                        System.out.println("头部的数据为："+o);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:

                    break;
            }
        }
        System.out.println("程序退出！");
    }
}
