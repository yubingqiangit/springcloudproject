package com.yu.designmodel.staticproxy;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/18 10:27 
 */
public class ProxyObject implements ProxyInteface {

    private ProxyInteface proxyInteface;

    public ProxyObject(ProxyInteface proxyInteface) {
        this.proxyInteface = proxyInteface;
    }

    @Override
    public void marry() {
        System.out.println("代理对象执行开始...");
        proxyInteface.marry();
        System.out.println("代理对象执行结束...");
    }
}
