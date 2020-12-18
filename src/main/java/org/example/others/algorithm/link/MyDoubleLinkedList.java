package org.example.others.algorithm.link;

import org.example.others.domin.HeroNode2;

public class MyDoubleLinkedList {

    private HeroNode2 head = new HeroNode2(0,"","");

    public HeroNode2 getHead() {
        return head;
    }

    /**
     * 遍历
     */
    public void show(){
        if (head.next == null){
            System.out.println("链表为空！");
            return;
        }
        HeroNode2 temp = head.next;
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 添加一个节点到最后
     * @param node 添加的节点
     */
    public void add(HeroNode2 node){
        HeroNode2 temp = head;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }

    /**
     * 更新一个节点
     * @param node 更新的节点
     */
    public void update(HeroNode2 node){
        if (head.next == null){
            System.out.println("链表为空！无法修改");
            return;
        }
        HeroNode2 temp = head.next;
        while (temp != null){
            if (temp.no == node.no){
                temp.name = node.name;
                temp.nickName = node.nickName;
                return;
            }
            temp = temp.next;
        }
        System.out.println("未找到"+node.no+"号英雄！");
    }

    /**
     * 根据no删除一个节点
     * @param no 要删除节点的no
     */
    public void delete(int no){
        if (head.next == null){
            System.out.println("链表为空，我发删除！");
            return;
        }
        HeroNode2 temp = head.next;
        while (temp != null){
            if (temp.no == no){
                temp.pre.next = temp.next;
                if (temp.next == null){
                    temp.pre = null;
                    temp.next = null;
                    return;
                }
                temp.next.pre = temp.pre;
                temp.pre = null;
                temp.next = null;
                return;
            }
            temp = temp.next;
        }
        System.out.println("要删除的"+no+"节点不存在！");
    }


}
