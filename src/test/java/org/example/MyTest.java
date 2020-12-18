package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Author: Derek
 * @DateTime: 2020/9/14 17:09
 * @Description: Test
 */
public class MyTest {

    @Test
    public void test(){

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Collections.reverse(list);
        System.out.println(list);

    }

}
