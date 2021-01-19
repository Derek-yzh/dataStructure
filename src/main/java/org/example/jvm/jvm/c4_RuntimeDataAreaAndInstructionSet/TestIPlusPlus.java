package org.example.jvm.jvm.c4_RuntimeDataAreaAndInstructionSet;

public class TestIPlusPlus {
    public static void main(String[] args) {
        int i = 8;
        //i = i++;
        i = ++i;
        System.out.println(i);
    }
}
