package com.yu.designmodel.garnisher;/**
 *
 * @author yubingqian
 * @date 2020-12-18 9:57
 *
 */

/**
 * 香肠类
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/18 9:57 
 */
public class Sausage extends Food{
    private Food food;

    public Sausage(Food food) {
        this.food = food;
    }

    public String make() {
        return food.make()+" + 香肠";
    }
}
