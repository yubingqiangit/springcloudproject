package com.yu.designmodel.garnisher;

/**
 * 面包类
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/18 9:54 
 */
public class Bread extends Food{
    private Food food;

    public Bread(Food food) {
        this.food = food;
    }

    public String make() {
        return food.make() + " + 面包";
    }

}
