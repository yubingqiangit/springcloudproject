package com.yu.serviceImpl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.yu.config.AlipayConfig;
import com.yu.model.PayReqItem;
import com.yu.service.AlipayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/25 11:03 
 */
@Service
public class AlipayServiceImpl implements AlipayService {

    private static final Logger log = LoggerFactory.getLogger(AlipayServiceImpl.class);

    /**
     * 支付宝支付调用接口
     * @throws IOException
     */
    @Override
    public String aliPay(PayReqItem payReqItem) throws IOException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest aliPayRequest = new AlipayTradePagePayRequest();
        aliPayRequest.setReturnUrl(AlipayConfig.return_url); //支付宝回调地址
        aliPayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //付款金额，从前台获取，必填
        //订单名称，必填
        String subject = new String("沙箱环境测试");
        aliPayRequest.setBizContent("{\"out_trade_no\":\"" + payReqItem.getOrderNo() + "\","
                + "\"total_amount\":\"" + payReqItem.getAmount() + "\","
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
        log.info("返回结果={}",result);
        return result;
    }

    @Override
    public void aliPay(String orderNo,String amount) throws IOException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest aliPayRequest = new AlipayTradePagePayRequest();
        aliPayRequest.setReturnUrl(AlipayConfig.return_url); //支付宝回调地址
        aliPayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //付款金额，从前台获取，必填
        //订单名称，必填
        String subject = new String("沙箱环境测试");
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
        log.info("返回结果={}",result);
    }
}
