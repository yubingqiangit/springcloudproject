package com.yu.designmodel.observer;/**
 *
 * @author yubingqian
 * @date 2020-12-18 9:24
 *
 */

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/18 9:24 
 */
public class LaoLi implements Person {
    private String name = "LaoLi";

    public LaoLi() {

    }
    @Override
    public void passMessage(String message) {
        System.out.println(name + "收到消息=========>" + message);

    }
}
