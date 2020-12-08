package com.yu.annotation.strategyImpl;

import com.yu.annotation.anconfig.OrderChannel;
import com.yu.annotation.strategy.OrderStrategy;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/8 15:20 
 */
@OrderChannel(channel = 1)
public class JD implements OrderStrategy {
    @Override
    public Object excute(String type) {
        return "JD"+ type;
    }
}
