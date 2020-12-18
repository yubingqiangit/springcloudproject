package com.yu.designmodel.observer;/**
 *
 * @author yubingqian
 * @date 2020-12-18 9:28
 *
 */

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/18 9:28 
 */
public class LaoWang implements Person {

    private String name = "LaoWang";

    public LaoWang() {

    }
    @Override
    public void passMessage(String message) {

        System.out.println(name + "收到消息==========>" +message);

    }
}
