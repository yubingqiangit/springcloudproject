package com.yu.designmodel.staticproxy;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/18 10:30 
 */
public class ProxyTest {
    public static void main(String[] args) {
        ProxyObject proxyObject = new ProxyObject(new BeiProxyObject());
        proxyObject.marry();
    }
}
