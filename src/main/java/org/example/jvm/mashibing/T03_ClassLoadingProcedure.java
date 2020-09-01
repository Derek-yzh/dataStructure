package org.example.jvm.mashibing;

/**
 * 类加载 loading -- linking -- initializing
 *
 * Linking
 */
public class T03_ClassLoadingProcedure {

    public static void main(String[] args) {

        System.out.println(T.count);//2
        System.out.println(T2.count);//3

    }

    static class T {
        public static T t = new T();
        public static int count = 2;

        private T(){
            count++;
        }

    }

    static class T2 {
        public static int count = 2;
        public static T2 t = new T2();

        private T2(){
            count++;
        }

    }

    static class T3 {
        public static int count = 2;
        public static T3 t = new T3();



        private T3(){
            count++;
        }

    }

}
