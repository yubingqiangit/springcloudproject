package com.yu.model;

/**
 * 支付请求参数
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/28 9:08 
 */
public class PayReqItem {
    private String orderNo;
    private String amount;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
