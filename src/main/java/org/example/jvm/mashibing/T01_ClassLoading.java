package org.example.jvm.mashibing;

/**
 * 2020-08-21 23:17:31
 * 类加载器
 */
public class T01_ClassLoading {

    public static void main(String[] args) {
        Class<T01_ClassLoading> aClass = T01_ClassLoading.class;

        System.out.println(aClass.getClassLoader());//App类加载器
        System.out.println(aClass.getClassLoader().getParent());//Extension类加载器
        System.out.println(aClass.getClassLoader().getClass().getClassLoader());//Bootstrap类加载器
        System.out.println(aClass.getClassLoader().getParent().getParent());//Bootstrap类加载器  C++实现
        System.out.println(String.class.getClassLoader());


        System.out.println("================================");
        System.out.println("-----------Boot--------------");
        String pathBoot = System.getProperty("sun.boot.class.path");
        System.out.println(pathBoot.replace(";",System.lineSeparator()));
        System.out.println("-----------Ext--------------");
        String pathExt = System.getProperty("java.ext.dirs");
        System.out.println(pathExt.replace(";",System.lineSeparator()));
        System.out.println("------------App-------------");
        String pathApp = System.getProperty("java.class.path");
        System.out.println(pathApp.replace(";",System.lineSeparator()));
        System.out.println("-------------------------");

        int x = 2;
        System.out.println(x << 1 | 1);

    }

}

