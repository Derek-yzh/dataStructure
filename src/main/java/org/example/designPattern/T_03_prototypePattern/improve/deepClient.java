package org.example.designPattern.T_03_prototypePattern.improve;

/**
 * 2020-07-15 23:42:25
 * 深拷贝
 */
public class deepClient {
    public static void main(String[] args) {
        DeepProtoType deepProtoType = new DeepProtoType("aaa");
        Person person = new Person("哈哈哈");
        deepProtoType.setPerson(person);

        System.out.println("第一种方式使用clone方法：");
        DeepProtoType deepProtoType2 = (DeepProtoType) deepProtoType.clone();
        DeepProtoType deepProtoType3 = (DeepProtoType) deepProtoType.clone();
        System.out.println(deepProtoType == deepProtoType2);
        System.out.println(deepProtoType.getPerson() == deepProtoType3.getPerson());

        System.out.println("第二种方式使用对象的序列化实现(推荐)：");
        DeepProtoType clone = (DeepProtoType) deepProtoType.deepClone();
        System.out.println(deepProtoType == clone);
        System.out.println(deepProtoType.getPerson() == clone.getPerson());
    }
}
