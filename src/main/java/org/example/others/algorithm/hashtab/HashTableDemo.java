package org.example.others.algorithm.hashtab;

/**
 * 2020-07-12 09:44:00
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(6);
        hashTab.add(new Emp(1,"aaa"));
        hashTab.add(new Emp(7,"七"));
        hashTab.add(new Emp(2,"bbb"));
        hashTab.add(new Emp(3,"ccc"));
        //hashTab.list();
        System.out.println(hashTab.findById(3));
    }
}

class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size;
    public HashTab(int size) {
        this.empLinkedListArray = new EmpLinkedList[size];
        this.size = size;
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加元素
    public void add(Emp emp){
        int index = hashFun(emp.getId());
        empLinkedListArray[index].add(emp);
    }

    //遍历哈希表
    public void list(){
        for (int i = 0; i < size; i++) {
            System.out.println("第"+(i+1)+"条链表=>");
            empLinkedListArray[i].list();
        }
    }

    //编写散列函数
    public int hashFun(int id){
        return id % size;
    }

    public Emp findById(int id){
        int index = hashFun(id);
        return empLinkedListArray[index].findById(id);
    }

}

//员工类
class Emp{
    private int id;
    private String name;
    private Emp next;
    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Emp getNext() {
        return next;
    }
    public void setNext(Emp next) {
        this.next = next;
    }
    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class EmpLinkedList{
    private Emp head;
    /**
     * 添加员工
     * 添加到链表最后
     * @param emp emp
     */
    public void add(Emp emp){
        if (head == null){
            head = emp;
            return;
        }
        Emp temp = head;
        while (temp.getNext() != null){
            temp = temp.getNext();
        }
        temp.setNext(emp);
    }
    public void list(){
        if (head == null){
            return;
        }
        Emp temp = head;
        while (temp != null){
            System.out.print(temp);
            temp = temp.getNext();
        }
        System.out.println();
    }
    public Emp findById(int id){
        if (head == null){
            return null;
        }
        Emp temp = head;
        while (temp != null && temp.getId() != id){
            temp = temp.getNext();
        }
        return temp;
    }
}