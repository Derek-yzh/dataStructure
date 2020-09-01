package org.example.my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Person{
    static{
        System.out.println("person");
    }
    {
        System.out.println("111");
    }
}
class Child extends Person{
    static {
        System.out.println("Child");
    }
    {
        System.out.println(2222);
    }
}

