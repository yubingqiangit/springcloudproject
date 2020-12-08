package com.yu.annotation.strategyImpl;

import com.yu.annotation.anconfig.OrderChannel;
import com.yu.annotation.strategy.OrderStrategy;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/8 15:23 
 */
@OrderChannel(channel = 2)
public class TB implements OrderStrategy {
    @Override
    public Object excute( String type) {
        return "TB" + type;
    }
}
