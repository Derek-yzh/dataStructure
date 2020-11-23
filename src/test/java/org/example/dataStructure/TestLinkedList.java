package org.example.dataStructure;

import org.example.algorithm.link.MyDoubleLinkedList;
import org.example.algorithm.link.SingleLinkedList;
import org.example.algorithm.link.SingleRingLinkedList;
import org.example.domin.HeroNode;
import org.example.domin.HeroNode2;
import org.junit.jupiter.api.Test;


/**
 * 2020-07-03 09:21:31
 * 测试链表
 */
public class TestLinkedList
{

    /**
     * 单链表基本操作测试
     */
    @Test
    public void testSingleLinkedList() {
        SingleLinkedList list = new SingleLinkedList();
        list.add(new HeroNode(1,"孙悟空","猴子"),true);
        list.add(new HeroNode(7,"紫霞","仙子"),true);
        list.add(new HeroNode(9,"韩信","点兵"),true);
        list.show();

        list.update(3,"哈哈哈");
        System.out.println("======update后======");
        list.show();

        /*//链表删除检验
        HeroNode delete1 = list.delete(5);
        HeroNode delete2 = list.delete(2);
        HeroNode delete3 = list.delete(3);
        System.out.println("======delete后======");
        list.show();
        System.out.println("删除的英雄为："+delete1.name);
        System.out.println("删除的英雄为："+delete2.name);
        System.out.println("删除的英雄为："+delete3.name);
        list.delete(3);*/

        //查找单链表中倒数第k个节点
        System.out.println("倒数第1个："+list.findInvertNum(1));
        System.out.println("倒数第3个："+list.findInvertNum(3));
        System.out.println("倒数第5个："+list.findInvertNum(5));

        //单链表反转
        /*list.reverse();
        System.out.println("反转后生成的数组：");
        list.show();*/

        System.out.println("倒序打印：");
        System.out.println("节点个数："+list.size());
        list.reversePrint();

    }

    /**
     * 合并两个有序单链表
     */
    @Test
    public void mergeTwoSingleLinkedLIst(){
        SingleLinkedList list1 = new SingleLinkedList();
        list1.add(new HeroNode(5,"孙悟空","猴子"),true);
        list1.add(new HeroNode(3,"紫霞","仙子"),true);
        list1.add(new HeroNode(4,"韩信","点兵"),true);
        SingleLinkedList list2 = new SingleLinkedList();
        list2.add(new HeroNode(1,"33","33"),true);
        list2.add(new HeroNode(3,"22","22"),true);
        list2.add(new HeroNode(9,"11","11"),true);

        SingleLinkedList list = new SingleLinkedList();
        HeroNode l1 = list1.getHead().next;
        HeroNode l2 = list2.getHead().next;
        while (l1 != null && l2 != null){
            if (l1.no < l2.no){
                list.add(new HeroNode(l1.no,l1.name,l1.nickName));
                l1 = l1.next;
            }else {
                list.add(new HeroNode(l2.no,l2.name,l2.nickName));
                l2 = l2.next;
            }
        }
        if (l1 == null){
            while (l2 != null){
                list.add(new HeroNode(l2.no,l2.name,l2.nickName));
                l2 = l2.next;
            }
        }else {
            while (l1 != null){
                list.add(new HeroNode(l1.no,l1.name,l1.nickName));
                l1 = l1.next;
            }
        }
        list.show();

    }

    /**
     * 双向链表测试
     */
    @Test
    public void testDoubleLinkedList(){
        MyDoubleLinkedList list = new MyDoubleLinkedList();
        list.add(new HeroNode2(6,"孙悟空","猴子"));
        list.add(new HeroNode2(3,"紫霞","仙子"));
        list.add(new HeroNode2(9,"韩信","点兵"));
        list.delete(3);
        list.update(new HeroNode2(9,"qqq","qqq"));
        list.delete(6);
        list.show();
    }

    /**
     * 约瑟夫问题
     * 单向环形链表测试
     */
    @Test
    public void testSingleRIngLinkedList(){
        SingleRingLinkedList list = new SingleRingLinkedList();
        list.addBoys(5);
        /*for (int i = 0; i < 5; i++) {
            System.out.println(list.removeAndMove(list.get(3)));
        }*/
        list.countBoy(1,2,5);
        list.addEnd(5);
        list.countBoy(1,2,1);
    }
}
