package com.yu.annotation.test;

import com.yu.annotation.factory.OrderStrategyFactory;
import com.yu.annotation.strategy.OrderStrategy;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/8 15:25 
 */
public class AnnotationTest {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        OrderStrategy orderStrategy = OrderStrategyFactory.getInstance().getStrategy(2);
        Object jd = orderStrategy.excute("do....");
        System.out.println(jd);
    }
}
