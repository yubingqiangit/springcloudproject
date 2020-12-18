package com.yu.designmodel.garnisher;

/**
 * 香菜类
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/18 9:59 
 */
public class Coriander extends Food {
    private Food food;

    public Coriander(Food food) {
        this.food = food;
    }

    public String make() {
        return food.make()+" + 香菜 ";
    }
}
