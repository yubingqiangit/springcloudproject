package com.yu.model;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/28 9:11 
 */
public class PayRespItem {
    private String orderNo;
    private String status;
    private String result;  //支付宝返回form

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
