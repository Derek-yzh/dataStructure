package org.example.my;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
class User{
    private Integer id;
    private String userName;
    private int age;
}
/**
 * 2020-07-08 17:32:34
 * 链式编程+流式计算
 *
 * 题目:请按照给出数据，找出同时满足以下条件的用户，也即以下条件全部满足
 *     偶数ID且年龄大于24且用户名转为大写且用户名字母倒排序,只输出一个用户名字
 *
 */
public class T_01_StreamAndFunction {
    public static void main(String[] args) {
        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(16, "e", 26);

        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);
        list.stream()
                .filter(u->u.getId()%2==0)
                .filter(u->u.getAge()>24)
                .map(u->u.getUserName().toUpperCase())
                .sorted((uOne,uTwo)-> uTwo.compareTo(uOne))
                .limit(2)
                .forEach(System.out::println);

        System.out.println("========函数式接口 ========");
        /**
         * 函数式接口:
         *   Consumer<T>消费型接口
         *    对类型为T的对象应用操作,包含方法:void accept(T t);
         *   Supplier<T>供给型接口
         *     返回类型为T的对象,包含方法::T get();
         *   Function<T, R>函数型接口
         *     对类型为T的对象应用操作,并返回结果.结果是R类型的对象包含方法: R apply(T t);
         *   Predicate<T>断定型接口
         *     确定类型为T的对象是否满足某约束，并返回boolean值。包含方法boolean test(T t);
         *
         */
        //Function<String,Integer> function = String::length;//函数型接口
        Function<String,String> function = (s)-> "function:"+s;//函数型接口
        System.out.println(function.apply("abc"));

        Predicate<String> predicate = String::isEmpty;//断定型接口
        System.out.println(predicate.test("aa"));

        Consumer<String> consumer = System.out::println;
        consumer.accept("consumer");

        Supplier<String> supplier = ()-> "a";
        System.out.println(supplier.get());


    }
}


