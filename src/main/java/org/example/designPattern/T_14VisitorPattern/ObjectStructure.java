package org.example.designPattern.T_14VisitorPattern;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Derek
 * @DateTime: 2020/12/15 19:49
 * @Description: TODO
 */
public class ObjectStructure {

    private List<Person> persons = new LinkedList<>();

    public void attach(Person p){
        persons.add(p);
    }

    public void detach(Person p){
        persons.remove(p);
    }

    public void display(Action action){
        for (Person person : persons) {
            person.accept(action);
        }
    }

}
