package org.example.jvm.mashibing;

/**
 * -Xmixed:混合模式 3375    默认
 * -Xint:纯解释模式         执行巨慢  启动快
 * -Xcomp:纯编译模式3221    执行快    启动慢
 */
public class T02_WayToRun {

    public static void main(String[] args) {

        for (int i = 0; i < 10_0000; i++) {
            m();
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10_0000; i++) {
            m();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);


    }

    /**
     * 耗时间用
     */
    private static void m() {
        for (long i = 0; i < 10_0000L; i++) {
            long j = i%3;
        }
    }

}
