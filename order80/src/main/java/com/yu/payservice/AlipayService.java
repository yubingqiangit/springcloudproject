package com.yu.payservice;

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
     * @param response
     * @param request
     * @throws IOException
     */
    void  aliPay(HttpServletResponse response, HttpServletRequest request) throws IOException;
}