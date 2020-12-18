package com.yu.designmodel.singleton;

/**
 * 饿汉模式(线程安全) 【推荐】
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/18 9:17 
 */
public class SingleTon {
    private static SingleTon instance = new SingleTon();

    public SingleTon(){}

    public static SingleTon getInstance() {
        return instance;
    }
}

