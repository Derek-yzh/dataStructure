package org.example;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Object内存模型 + 锁升级过程   （前4s不会开启偏向锁）
     */
    @Test
    public void shouldAnswerWithTrue() throws InterruptedException {

        Thread.sleep(5000);//前4s不会开启偏向锁

        Object o = new Object();

        //System.out.println(o.hashCode());//计算hashcode后将不再有偏向锁

        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

    }

    /**
     * 测试uHashSet
     */
    static class TestHashSet{
        private String test;
        public TestHashSet(String test) {
            this.test = test;
        }
        public String getTest() {
            return test;
        }
        public void setTest(String test) {
            this.test = test;
        }
        @Override
        public String toString() {
            return "TestHashSet{" +
                    "test='" + test + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            return true;
            /*if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestHashSet that = (TestHashSet) o;
            return test != null ? test.equals(that.test) : that.test == null;*/
        }

        @Override
        public int hashCode() {
            return test != null ? test.hashCode() : 0;
        }
    }
    @Test
    public void testHashCode(){
        System.out.println("重地".hashCode());//1179395
        System.out.println("通话".hashCode());//1179395
        HashSet<Object> set = new HashSet<>();
        set.add("重地");
        set.add("通话");
        set.add(new TestHashSet("重地"));
        set.add(new TestHashSet("通话"));
        for (Object o : set) {
            System.out.println(o);
        }
    }


    @Test
    public void tie(){
        String s = "aa";
        String s2 = s;
        System.out.println(s.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s == s2);
    }




    @Test
    public void t_2020_0729(){

        Integer a1 = 100;
        Integer a2 = 100;
        Integer b1 = 200;
        Integer b2 = 200;

        System.out.println(a1 == a2);
        System.out.println(b1 == b2);

        System.out.println(a1 >> 1 | 1);

    }

    @Test
    public void testThreads() throws InterruptedException {
        Thread a = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("A" + i);
            }
        }, "A");


        Thread b = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("B" + i);
            }
        }, "B");

        a.start();
        b.start();
        a.join();
        b.join();
    }



























}
