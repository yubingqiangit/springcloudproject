package com.yu.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * TODO
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/28 9:08 
 */

@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pay_pay")
@Data
public class PayPay extends Model<PayPay> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("amount")
    private String amount;

    @TableField("common")
    private String common;

    @TableField("state")
    private Integer state;

    @TableField("orderNo")
    private String orderNo;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
