package com.yu.designmodel.template;

import com.yu.utils.Dict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 摸板方法子类
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/24 16:41 
 */
public class Potato extends AbsTemplateBase {

    private static final Logger logger = LoggerFactory.getLogger(Potato.class);
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Potato(Integer type) {
        this.type = type;
    }

    /**
     * 放入食材，个性化方法，子类重写实现
     */
    @Override
    void putVegetables() {
        logger.info("放入土豆....");
    }

    /**
     * 放入调料，个性化方法，子类重写实现
     */
    @Override
    void putCondiments() {
        logger.info("放入葱姜蒜大辣椒....");
    }

    /**
     * 加醋，非
     */
    @Override
    void addVinegar() {
        logger.info("放入醋.....");
    }

    /**
     * 重写钩子函数，实现自定义功能
     * @return
     */
    boolean customerWantsCondiments(){
        if (Dict.SUCCESS.equals(this.type))
            return true;
        else
            return false;
    }
}
