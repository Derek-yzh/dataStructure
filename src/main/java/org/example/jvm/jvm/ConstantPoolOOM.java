package org.example.jvm.jvm;

import java.util.LinkedList;
import java.util.List;

public class ConstantPoolOOM {
    static long i = 0;
    public static void main(String[] args) {
        List<String> strings = new LinkedList<>();
        for (;;) strings.add("" + i++);
    }
}
