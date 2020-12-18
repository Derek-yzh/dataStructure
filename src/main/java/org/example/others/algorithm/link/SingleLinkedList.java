package org.example.others.algorithm.link;

import org.example.others.domin.HeroNode;

import java.util.Stack;


/**
 * 2020-07-03 09:21:31
 * 单链表
 */
public class SingleLinkedList {

    //初始化的一个头节点，头节点不要动，不存放具体数据
    private HeroNode head = new HeroNode(0,"","");

    /**
     * 添加节点
     * @param heroNode 添加的节点
     */
    public void add(HeroNode heroNode){
        HeroNode temp = head;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    /**
     * 是否按序添加节点
     * @param heroNode 要添加的节点
     * @param isSort 是否按序添加 true为是
     */
    public void add(HeroNode heroNode,boolean isSort){
        if (isSort){
            HeroNode temp = head;
            while (temp.next != null){
                if (heroNode.no == temp.next.no){
                    System.out.println("第"+heroNode.no+"已有人啦！ "+heroNode.name+"未能添加！");
                    return;
                } else if (temp.next.no > heroNode.no){
                    heroNode.next = temp.next;
                    temp.next = heroNode;
                    return;
                }
                temp = temp.next;
            }
            temp.next = heroNode;
        }else {
            add(heroNode);
        }
    }

    /**
     * 展示链表
     */
    public void show(){
        if (head.next == null){
            return;
        }
        HeroNode temp = head.next;
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 根据no修改名字
     * @param no no
     * @param name 要修改的名字
     */
    public void update(int no, String name){
        if (head.next == null){
            System.out.println("链表为空！无法修改");
        }else {
            HeroNode temp = head.next;
            while (temp != null){
                if (temp.no == no){
                    temp.name = name;
                    return;
                }
                temp = temp.next;
            }
            System.out.println("未找到"+no+"号英雄！");
        }
    }

    /**
     * 根据no删除一个节点
     * @param no no
     * @return 删除的节点
     */
    public HeroNode delete(int no){
        HeroNode heroNode = null;
        if (head.next == null){
            System.out.println("链表为空，无法删除！");
        }else {
            HeroNode temp = head;
            while (temp.next != null){
                if (temp.next.no == no){
                    heroNode = temp.next;
                    temp.next = temp.next.next;
                    break;
                }
                temp = temp.next;
            }
        }
        return heroNode;
    }

    /**
     * 链表数据个数
     * @return 数据个数
     */
    public int size(){
        if (head.next == null){
            return 0;
        }
        int num = 0;
        HeroNode temp = head.next;
        while (temp != null){
            num++;
            temp = temp.next;
        }
        return num;
    }

    /**
     * 查找单链表中倒数第k个节点
     * @param index 查询的倒数索引
     * @return 查询到的节点
     */
    public HeroNode findInvertNum(int index){
        if (head.next == null){
            return null;
        }
        if (index > this.size() || index <= 0){
            return null;
        }
        HeroNode temp = head.next;
        index = this.size() - index;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 单链表反转(通过产生一条新链表返回)
     * @return 反转生成的新链表
     */
    public SingleLinkedList reverseNew(){
        SingleLinkedList reverseList = new SingleLinkedList();
        HeroNode temp = head.next;
        while (temp != null){
            HeroNode hero = new HeroNode(temp.no,temp.name,temp.nickName);
            reverseList.insertTop(hero);
            temp = temp.next;
        }
        return reverseList;
    }

    /**
     * 单链表反转
     */
    public void reverse(){
        if (head.next == null || head.next.next == null){
            return;
        }
        HeroNode reverse = new HeroNode(0,"","");
        while (head.next != null){
            HeroNode first = this.delFirst();
            first.next = reverse.next;
            reverse.next = first;
        }
        head.next = reverse.next;
        reverse.next = null;
    }
    public void reverse2(){
        if (head.next == null || head.next.next == null){
            return;
        }
        HeroNode reverse = new HeroNode(0,"","");
        HeroNode cur = head.next;
        HeroNode next = null;
        while (cur != null){
            next = cur.next;
            cur.next = reverse.next;
            reverse.next = cur;
            cur = next;
        }
        head.next = reverse.next;
        reverse.next = null;
    }

    /**
     * 删除第一个节点并返回
     * @return 返回第一个节点
     */
    public HeroNode delFirst(){
        if (head.next == null){
            return null;
        }
        HeroNode first = head.next;
        head.next = first.next;
        return first;
    }

    /**
     * 插入一个节点到首部
     * @param heroNode 插入的节点
     */
    public void insertTop(HeroNode heroNode){
        HeroNode temp = head;
        heroNode.next = temp.next;
        temp.next = heroNode;
    }

    /**
     * 倒序打印
     */
    public void reversePrint(){
        if (head.next == null){
            return;
        }
        HeroNode cur = head.next;
        Stack<HeroNode> stack = new Stack<>();
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }
}


