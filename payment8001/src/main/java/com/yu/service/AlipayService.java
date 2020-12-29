package com.yu.service;

import com.yu.model.PayReqItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/25 11:02 
 */
public interface AlipayService {
    /**
     * 支付宝支付调用接口
     * @param payReqItem
     * @throws IOException
     */
    String  aliPay(PayReqItem payReqItem) throws IOException;

    void  aliPay(String orderNo,String amount) throws IOException;
}