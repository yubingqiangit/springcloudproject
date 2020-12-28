package com.yu.utils;/**
 *
 * @author yubingqian
 * @date 2020-12-28 14:51
 *
 */

import java.util.Random;
import java.util.UUID;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/28 14:51 
 */
public class OrderNoRandom {

    public static OrderNoRandom instance;

    public static OrderNoRandom getInstance(){
        if (instance == null) {
            instance = new OrderNoRandom();
        }
        return instance;
    }



    public static String getNo() {
       return  UUID.randomUUID().toString().replace("-","");
    }


}
