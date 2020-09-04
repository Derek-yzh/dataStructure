package org.example.juc_YangGe.more;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: Derek
 * @DateTime: 2020/9/4 11:07
 * @Description: use AtomicReference
 */
public class T_AtomicReference {

    public static void main(String[] args) {
        User user1 = new User("aaa", 12);
        User user2 = new User("bbb", 77);

        AtomicReference<User> user = new AtomicReference<>();
        user.set(user1);

        System.out.println(user.compareAndSet(user1,user2)+"\t"+user.get().toString());
        System.out.println(user.compareAndSet(user1,user2)+"\t"+user.get().toString());



    }

}

@Getter
@ToString
@AllArgsConstructor
class User{
    String name;
    int age;
}
