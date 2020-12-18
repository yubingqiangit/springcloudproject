package com.yu.designmodel.observer;/**
 *
 * @author yubingqian
 * @date 2020-12-18 9:36
 *
 */

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/18 9:36 
 */
public class ObserverTest {

    public static void main(String[] args) {
        Person laoLi = new LaoLi();
        Person laoWang = new LaoWang();
        XiaoMei xiaoMei = new XiaoMei();
        xiaoMei.addPersons(laoLi);
        xiaoMei.addPersons(laoWang);
        xiaoMei.sendMessage();

    }
}
