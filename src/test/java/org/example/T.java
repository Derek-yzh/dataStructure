package org.example;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 * @Author: Derek
 * @DateTime: 2021/2/28 9:48
 * @Description: TODO
 */
public class T {

    public static int jumpFloor(int target) {
        if (target == 0) return 1;
        if (target < 0) return 0;
        int res1 = jumpFloor(target - 1);
        int res2 = jumpFloor(target - 2);
        return res1 + res2;
    }

    @SneakyThrows
    public static void main(String[] args) {

        Scanner read = new Scanner(System.in);
        while (true){
            int input = read.nextInt();
            System.out.println(input);
        }

    }

}
