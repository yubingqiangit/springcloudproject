package com.yu.payserviceimpl;/**
 *
 * @author yubingqian
 * @date 2020-12-25 11:03
 *
 */

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.yu.config.AlipayConfig;
import com.yu.payservice.AlipayService;
import com.yu.utils.AliPayClientUtils;
import com.yu.utils.OrderNoRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/25 11:03 
 */
@Service
public class AlipayServiceImpl implements AlipayService {

    private static final Logger log = LoggerFactory.getLogger(AlipayServiceImpl.class);

    @Autowired
    AlipayConfig alipayConfig;

    /**
     * 支付宝支付调用接口
     * @param response
     * @param request
     * @throws IOException
     */
    @Override
    public void aliPay(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //获得初始化的AlipayClient
        AlipayClient alipayClient = AliPayClientUtils.getInstance().initAlipayClient(alipayConfig);
        //AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.gatewayUrl, alipayConfig.app_id, alipayConfig.merchant_private_key, "json", alipayConfig.charset, alipayConfig.alipay_public_key, alipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest aliPayRequest = new AlipayTradePagePayRequest();
        aliPayRequest.setReturnUrl(alipayConfig.return_url); //支付宝回调地址
        aliPayRequest.setNotifyUrl(alipayConfig.notify_url);
        //String orderNo = request.getParameter("orderNo");
        String amount = request.getParameter("amount");
        //商户订单号，后台可以写一个工具类生成一个订单号，必填
        String orderNo = OrderNoRandom.getNo();

        //订单名称，必填
        String subject = new String("支付宝测试环境...");
        aliPayRequest.setBizContent("{\"out_trade_no\":\"" + orderNo + "\","
                + "\"total_amount\":\"" + amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = null;
        try {
            result = alipayClient.pageExecute(aliPayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        //输出
        out.println(result);
        log.info("返回结果={}",result);
    }
}
