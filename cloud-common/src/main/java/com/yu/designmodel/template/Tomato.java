package com.yu.designmodel.template;

import com.yu.utils.Dict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 西红柿子类
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/24 16:56 
 */
public class Tomato extends AbsTemplateBase {

    private static final Logger logger = LoggerFactory.getLogger(Tomato.class);
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Tomato(Integer type) {
        this.type = type;
    }

    /**
     * 放入食材，个性化方法，子类重写实现
     */
    @Override
    void putVegetables() {
        logger.info("放入西红柿.....");
    }

    /**
     * 放入调料，个性化方法，子类重写实现
     */
    @Override
    void putCondiments() {
        logger.info("放入白糖...");

    }

    /**
     * 加醋，非
     */
    @Override
    void addVinegar() {
        logger.info("加牛奶....");

    }

    boolean customerWantsCondiments(){
        if (Dict.SUCCESS.equals(this.type))
            return true;
        else
            return false;
    }

}
