package org.example.jvm.mashibing;

/**
 * 用Jclasslib查看  指令
 */
public class T06_ {

    public static void main(String[] args) {

        int i = 8;
        i = i++;
        System.out.println(i);

        Object o = new Object();
        int hashCode = o.hashCode();

    }

}
