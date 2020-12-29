package com.yu.vo;

/**
 * 支付预下单
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/29 14:18 
 */
public class PayPay {

    private Integer id;
    private String amount;
    private String orderNo;
    private String common;
    private Integer state;

    @Override
    public String toString() {
        return "PayPay{" +
                "id=" + id +
                ", amount='" + amount + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", common='" + common + '\'' +
                ", state=" + state +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
