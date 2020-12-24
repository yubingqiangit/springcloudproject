package com.yu.designmodel.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * 摸板方法模式-基类
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/24 16:08 
 */
public abstract class AbsTemplateBase {
    private static final Logger logger = LoggerFactory.getLogger(AbsTemplateBase.class);

    /**
     * final修饰避免子类重写
     */
    final void todo() {
        AddWater();
        putVegetables();
        putCondiments();
        if (customerWantsCondiments())
            addVinegar();
        finished();

    }

    /**
     * 加水，子类通用方法，非必要重写
     */
    void AddWater() {
        logger.info("加水........");
    }

    /**
     * 放入食材，个性化方法，子类重写实现
     */
    abstract void putVegetables();

    /**
     * 放入调料，个性化方法，子类重写实现
     */
    abstract void putCondiments();

    /**
     * 加醋，非
     */
    abstract void addVinegar();

    void finished() {
        logger.info("做完了......");
    }

    /**
     * 钩子函数，实现自定义功能
     * @return
     */
    boolean customerWantsCondiments() {
        return true;
    }

}
