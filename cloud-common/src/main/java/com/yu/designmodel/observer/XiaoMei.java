package com.yu.designmodel.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者对象
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/18 9:22 
 */
public class XiaoMei {

    private static List<Person> persons = new ArrayList<>();

    public void addPersons(Person person) {
        persons.add(person);
    }

    public void sendMessage() {
        persons.stream().forEach(x->{
            x.passMessage("xiaomei send message!!!!!");
        });
    }

}
