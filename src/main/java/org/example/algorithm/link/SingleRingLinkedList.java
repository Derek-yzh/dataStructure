package org.example.algorithm.link;

import org.example.domin.Boy;

/**
 * @create 2020-07-04 11:49:28
 * Josepfu问题
 * 利用环形单向链表解决
 */
public class SingleRingLinkedList {

    private Boy first = null;

    /**
     * 添加一个元素到队尾
     * @param value 孩子的值
     */
    public void addEnd(int value){
        Boy boy = new Boy(value);
        if (first == null){
            first = boy;
            first.setNext(first);
            return;
        }
        Boy end = this.end();
        boy.setNext(first);
        end.setNext(boy);
    }

    /**
     * 根据nums添加nums个节点
     * @param nums 节点数量
     */
    public void addBoys(int nums){
        if (nums < 1){
            System.out.println("nums的值不正确！");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1){
                first = boy;
                curBoy = first;
            }else {
                curBoy.setNext(boy);
                curBoy = boy;
            }
        }
        curBoy.setNext(first);
    }

    /**
     * 遍历链表
     */
    public void show(){
        if (first == null){
            System.out.println("链表为空！");
            return;
        }
        Boy curBoy = first;
        while (true){
            if (curBoy.getNext() == first){
                System.out.println(curBoy);
                break;
            }
            System.out.println(curBoy);
            curBoy = curBoy.getNext();
        }
    }

    /**
     * @return 返回最后一个节点
     */
    public Boy end(){
        if (first == null){
            System.out.println("链表为空！");
            return null;
        }
        Boy curBoy = first;
        while (curBoy.getNext() != first){
            curBoy = curBoy.getNext();
        }
        return curBoy;
    }

    /**
     * 链表中数据数量
     * @return 返回数据总个数
     */
    public int size(){
        if (first == null){
            return 0;
        }
        int num = 1;
        Boy cur = first;
        while (cur.getNext() != first){
            cur = cur.getNext();
            num++;
        }
        return num;
    }

    /**
     * 删除节点并移动到该节点后一位
     * @param boy 要删除的节点
     * @return 返回删除的对象
     */
    public Boy removeAndMove(Boy boy){
        if (first == null){
            System.out.println("链表为空！");
            return null;
        }
        if (this.size() == 1){
            Boy remove = this.first;
            first = null;
            return remove;
        }
        Boy end = null;
        while (first != null){
            if (first.getValue() == boy.getValue()){
                end = this.end();
                first = end.getNext().getNext();
                break;
            }
            first = first.getNext();
        }
        Boy remove = end.getNext();
        end.setNext(first);
        return remove;
    }

    /**
     * 删除第index个位置的小孩
     * @param index 删除的索引
     * @return 返回删除的对象
     */
    public Boy remove(int index){
        if (index < 1){
            System.out.println("index值有问题！");
            return null;
        }
        if (first == null){
            System.out.println("链表为空！");
            return null;
        }
        Boy end = this.end();
        while (index > 1){
            end = end.getNext();
            index--;
        }
        Boy remove = end.getNext();
        end.setNext(end.getNext().getNext());
        if (index == 1){
            first = end;
        }
        return remove;
    }

    /**
     * 获取第index个对象
     * @param index 索引
     * @return 返回获取的对象
     */
    public Boy get(int index){
        if (index < 1){
            System.out.println("index值有问题！");
            return null;
        }
        if (first == null){
            System.out.println("链表为空！");
            return null;
        }
        if (index == 1){
            return first;
        }
        Boy end = this.end();
        while (index > 1){
            end = end.getNext();
            index--;
        }
        return end.getNext();
    }

    /**
     * 根据用户输入，计算出小孩出圈的顺序
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数记下
     * @param nums 表示出多少小孩
     */
    public void countBoy(int startNo, int countNum, int nums){
        if (first == null || startNo < 1 || startNo > nums || nums > this.size()){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
        }
        for (int i = 0; i < nums; i++) {
            System.out.println(this.removeAndMove(this.get(countNum)));
        }
    }

}
































