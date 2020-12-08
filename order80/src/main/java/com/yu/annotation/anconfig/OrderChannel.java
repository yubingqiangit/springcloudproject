package com.yu.annotation.anconfig;

import java.lang.annotation.*;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/8 15:16 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OrderChannel {
    int channel();
}
