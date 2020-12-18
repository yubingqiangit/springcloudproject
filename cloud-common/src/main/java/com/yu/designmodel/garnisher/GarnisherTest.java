package com.yu.designmodel.garnisher;

/**
 * 装饰者模式
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/18 10:00 
 */
public class GarnisherTest {

    public static void main(String[] args) {
        Food food = new Bread(new Sausage(new Coriander(new Food("奶油"))));
        System.out.println(food.make());
    }
}
