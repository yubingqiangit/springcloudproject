package com.yu.designmodel.garnisher;

/**
 * 装饰者模式
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/18 9:51 
 */
public class Food {
    private String foodName;

    public Food() {

    }
    public Food(String foodName) {
        this.foodName = foodName;
    }

    public String make() {
        return foodName;
    }
}
