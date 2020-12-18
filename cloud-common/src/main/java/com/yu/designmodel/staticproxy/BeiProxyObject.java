package com.yu.designmodel.staticproxy;

/**
 * 被代理对象
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/18 10:29 
 */
public class BeiProxyObject implements ProxyInteface {
    @Override
    public void marry() {
        System.out.println("被代理对象执行开始.....");
        System.out.println("被代理对象执行结束.....");
    }
}
