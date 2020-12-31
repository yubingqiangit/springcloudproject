package com.yu.controller;/**
 *
 * @author yubingqian
 * @date 2020-12-28 8:58
 *
 */

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.Data;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePageRefundRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradePageRefundResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.yu.common.CommonResult;
import com.yu.config.AlipayConfig;
import com.yu.dao.ModelMapper;
import com.yu.entity.ModelStakeRel;
import com.yu.exception.CommonException;
import com.yu.exception.ExceptionEnums;
import com.yu.model.PayReqItem;
import com.yu.model.PayRespItem;
import com.yu.service.AlipayService;
import com.yu.utils.AliPayClientUtils;
import com.yu.utils.OrderNoRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/28 8:58 
 */
@RestController
@RequestMapping("/payment")
public class PayController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    AlipayService alipayService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlipayConfig alipayConfig;

    /**
     * post请求
     * @param payReqItem
     * @return
     */

    @RequestMapping(value = "/pay",method = RequestMethod.POST)
    public CommonResult<PayRespItem> payMent(@RequestBody PayReqItem payReqItem) {
        try {
            logger.info("收到支付请求:{}", JSON.toJSONString(payReqItem));
            String result = alipayService.aliPay(payReqItem);
            logger.info("支付宝返回form标签:{}",result);
            //创建支付预下单
            int pay = modelMapper.createPay(payReqItem);
            logger.info("创建支付预下单成功....");

            PayRespItem payRespItem = new PayRespItem();
            payRespItem.setOrderNo(payReqItem.getOrderNo());
            payRespItem.setStatus("success");
            payRespItem.setResult(result);
            return new CommonResult<>(payRespItem);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }


    @RequestMapping(value = "/payget/{orderNo}/{amount}",method = RequestMethod.GET)
    public CommonResult<PayRespItem> payGet(@PathVariable("orderNo") String orderNo, @PathVariable("amount")String amount) {
        try {
            logger.info("method pay orderNo:{}amount:{}", orderNo,amount);
             alipayService.aliPay(orderNo,amount);
            PayRespItem payRespItem = new PayRespItem();
            payRespItem.setOrderNo(orderNo);
            payRespItem.setStatus("call pay success....");
            return new CommonResult<>(payRespItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 统一收单线下交易查询
     * @return
     */
    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public CommonResult<String> query(@RequestBody PayReqItem payReqItem) throws AlipayApiException {
        AlipayClient alipayClient = AliPayClientUtils.getInstance().initAlipayClient(alipayConfig);
       // AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.gatewayUrl, alipayConfig.app_id, alipayConfig.merchant_private_key, "json", alipayConfig.charset, alipayConfig.alipay_public_key, alipayConfig.sign_type);
        AlipayTradeQueryRequest  request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\""+payReqItem.getOrderNo()+"\"," +
                "      \"query_options\":[" +
                "        \"trade_settle_info\"" +
                "      ]" +
                "  }");
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        System.out.println( response.getBody());
        System.out.println(response.getOutTradeNo()+ "状态" + response.getTradeStatus() + "金额" + response.getTotalAmount());
        if(response.isSuccess()){
            System.out.println("调用成功");
            return new CommonResult<>(response.getBody());
        } else {
            System.out.println("调用失败");
        }
        throw new CommonException(ExceptionEnums.valueOf("ERROR"));
    }

    /**
     * app支付获取签名串
     * @return
     */
    @RequestMapping(value = "/app/str",method = RequestMethod.POST)
    public CommonResult<String> getAppStr(@RequestBody PayReqItem payReqItem) {
        //实例化客户端
        AlipayClient alipayClient = AliPayClientUtils.getInstance().initAlipayClient(alipayConfig);
        //AlipayClient alipayClient = new DefaultAlipayClient(url, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY,sign_type);
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest alirequest = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        //请保证OutTradeNo值每次保证唯一
        model.setOutTradeNo(payReqItem.getOrderNo());
        model.setTimeoutExpress("30m");
        model.setTotalAmount(payReqItem.getAmount());
        model.setProductCode("QUICK_MSECURITY_PAY");
        alirequest.setBizModel(model);
        alirequest.setNotifyUrl("http://haha.vaiwan.com/alipay/callback");
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse aliresponse = alipayClient.sdkExecute(alirequest);
            System.out.println("签名串："+aliresponse.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
            int pay = modelMapper.createPay(payReqItem);
            logger.info("创建支付预下单成功....");
            return new CommonResult<>(aliresponse.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 生成二维码
     * @param payReqItem
     */
    @RequestMapping(value = "/create/qrcode",method = RequestMethod.POST)
    public CommonResult<String> qrCode(@RequestBody PayReqItem payReqItem) {
        AlipayClient alipayClient = AliPayClientUtils.getInstance().initAlipayClient(alipayConfig);
        //AlipayClient alipayClient = new DefaultAlipayClient(url, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY,sign_type);
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("二维码测试数据");
        model.setSubject("App支付测试Java");
        //请保证OutTradeNo值每次保证唯一
        model.setOutTradeNo(payReqItem.getOrderNo());
        model.setTimeoutExpress("30m");
        model.setTotalAmount(payReqItem.getAmount());
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("http://haha.vaiwan.com/alipay/callback");
        //这里和普通的接口调用不同，使用的是sdkExecute
        AlipayTradeAppPayResponse response = null;
        try {
            response = alipayClient.sdkExecute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
        int width=100;//定义二维码的长宽
        int height=100;
        String formt="png";//图片格式
        //获得app支付生成的签名参数并赋值用于生成二维码
        String content=response.getBody();
        //定义二维码的参数
        HashMap<EncodeHintType,Object> hints=new HashMap<EncodeHintType,Object>();
        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");//二维码的编码格式
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);//二维码的纠错等级,纠错等级越高,则存储的信息越少,一般是指定M级
        hints.put(EncodeHintType.MARGIN,2);//设置二维码周围的空白内容

        //生成二维码
        try {
            Date date = new Date();
            String s = date.getTime()+"";
            BitMatrix bitMatrix=new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width,height,hints);
            Path file=new File("D:/appQrCode/"+s+".png").toPath();//将指定的二维码图片生成在指定的路径
            MatrixToImageWriter.writeToPath(bitMatrix,formt,file);
            System.out.println("生成成功");
            int pay = modelMapper.createPay(payReqItem);
            logger.info("创建支付预下单成功....");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CommonResult<>("success...");
    }

    public static void main(String[] args) throws IOException, AlipayApiException {
        // TODO Auto-generated method stub
        String APP_ID="2021000116682104";
        String APP_PRIVATE_KEY="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDKyuEGNQ7vIsuK8oyiu7+ahwJ2tvWaZTrB+cttDs4MjQvANpppALNrHNahbkAga52tuswAYimVVpAjAFFridiCg34017hfwQHAw2SgFVBamjB1NCmrYkwEyJGaY7InP4yThWwTG9p4I4bLgTz7Yj5xvXKIy0pl1hStqtO1WKGBU+Q9tJ+4y2PEpSys7iE9ZSbN0zLxqJzSJ0W76s3uckx7MmovYleiXkxu9zBkcfY1924cqzjiBRDQBVhCAnpiYMhcQwDF9spbqZb8kyPKH+Oc25S2wJeEdlBO3+CF3LfBdR/GsJuPphpwLF0Owrxg8OPAS9eRbwhJWz+R5tOkUEX3AgMBAAECggEBAIPpMDKfdNVs5sW9PGnmgp370Eh0fpIt4uimmgKYrMNRRCGF4y8GO68jFSHw21m7ZtfJOEXPlIkFw8BgxpWTFvjem8u3vsDahU9kSiIvexNUVM0IX0qSKCy8Yqnwy8Pn/INBOKm9ZoDbayUZLhe/Mis+NjBxPDW96bmF8yx1OKksReQ+rx8O/L92csvOinZMJplKc0frmsqBOeSirl+kTDDMVeL1qndiBBGWhZ+D8x/3GgOc54qFAU9UdbBVTXrsFppgPBYJQ1aZexWWeuJKxc5gN1d/TRKLpExe/pgV0x3nOrB6DNzoxQl2I7wMkxa7bIT3XRFI71yasvfHsLE7RrECgYEA8GESuWeBAaHeSRNlIj/Obvrk6Hx9hnVHla9N+3PJXWUIdOu/2VKyFfSn6ll6A6bnhAttgMg7gRMLIHnT0ycTBBkLnfLXEbuVzRR7yA3YZ6WzfraJpBqJHULqWJmFTZzgqGldm3HGyEnwS2w0lqvFNSHkKEYDALMgvCi3K7gPurUCgYEA1/iEVYXMjWgFb/BWdHuGeYeeq8ZswWnCwJ/U54Hcr/pPDcgQL+bLsfVQ2ginMsEn5ijiRxM25HgQRlVt/y6QPBAPMlyGoR82sU2UwVmj+aJymYP88AQJ6TgdAMnhn0c3M9s2Q4NKKj/q64NZwF5nrZuJBnMapNEUKrxdndpI7XsCgYEAzBuhKo0Ynao0dRhi55IGV3XyqPYMH/+1qQQR01Gdyg/f/mevn3j4fmwfqH4RfMhcDWMmuBNNc2wYJptLoh+rpe8eTh/FPulpaZnbASPAfUHWB+I3PYDKrG4FTvYMmrp6iIXvVe5mO+uMfn6CqO1xpowAj7HjGtT/FsX7wsOnl4ECgYBl+5lp1SOxhnRC1qfMm2P10V/Q14MVlf57/T2JEJ6Ijtdzt4xFQmPfperG+p0Rb3qKybWIQH+ERVnSDJdDj9ZWNnMmoeCFBvtJoEvlPZIb2GTrLxNqt02CzpeLfslG8FcV6qbmHIISD1q8WasxFqM4rKB45jOnrn8qawZ9+szDLwKBgEHMPkXFPWp/6XgjSOD66M7saY1JBCjwJ4UkwgU+jFIBIWs7GC1POtQEErnzN2FCGQkC83C8A4w8dBx2UJw8V+mNgdeWE2dda259R0pYF4MDe5JOzFCYitP0YoqG81tx0TtsNFfodBbQ5z937Q2XVN++DqAUk1Y/g4QISdm5gTaS";
        String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAm1ri3uNj3HiQ+rUxhHRJknuSx0FrkSFLkHXqyT5MmlGpIiZ4dVRfNRkp7f27ptmgzy6xBs0jKRPh381Oa4ydqjaDIeSZ+jxWCKsyYRZh3+cPzL91pb/XjCA3khIU6Vgp3IEKRMGq9VsONvqehu1BZeA5INW56Lh4bd8tV/pfPvy+w8MIniRbdsRleFKOSE6AdvljecJH5wzFT4GtsLRsaPG1QgWhJ7d1MnbiZzGSJNyVaGoCa175JC1c72mkdHp0s2/ClEAzlJfcwU78UC9pvBKL3763sNT6hDAibYlWwYjAcGJ0teKc6uaJmG53b7t8QbFQKq0EXQaD//FK8jjXIQIDAQAB";
        //签名方式
        String sign_type="RSA2";
        //编码格式
        String CHARSET="utf-8";
        //正式环境支付宝网关，如果是沙箱环境需更改成https://openapi.alipaydev.com/gateway.do
        String url="https://openapi.alipaydev.com/gateway.do";
        //实例化客户端

        System.out.println(APP_ID + "|" + APP_PRIVATE_KEY);
        AlipayClient alipayClient = new DefaultAlipayClient(url, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY,sign_type);
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("二维码测试数据");
        model.setSubject("App支付测试Java");
        //请保证OutTradeNo值每次保证唯一
        model.setOutTradeNo(getRandomFileName());
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("http://haha.vaiwan.com/alipay/callback");
        //这里和普通的接口调用不同，使用的是sdkExecute
        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
        System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
        int width=100;//定义二维码的长宽
        int height=100;
        String formt="png";//图片格式
        //获得app支付生成的签名参数并赋值用于生成二维码
        String content=response.getBody();
        //定义二维码的参数
        HashMap<EncodeHintType,Object> hints=new HashMap<EncodeHintType,Object>();
        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");//二维码的编码格式
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);//二维码的纠错等级,纠错等级越高,则存储的信息越少,一般是指定M级
        hints.put(EncodeHintType.MARGIN,2);//设置二维码周围的空白内容

        //生成二维码
        try {
            Date date = new Date();
            String s = date.getTime()+"";
            BitMatrix bitMatrix=new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width,height,hints);
            Path file=new File("D:/appQrCode/"+s+".png").toPath();//将指定的二维码图片生成在指定的路径
            MatrixToImageWriter.writeToPath(bitMatrix,formt,file);
            System.out.println("生成成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 生成随机数
     * @return
     */
    public static String getRandomFileName() {
        SimpleDateFormat simpleDateFormat;
        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        Random random = new Random();
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
        return rannum + str;
    }




}
