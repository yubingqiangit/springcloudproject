package com.yu.entity;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2021/1/18 10:36 
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pc_model_stake_rel")
@Data
public class ModelStakeRel extends Model<ModelStakeRel> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

   @TableField("model_id")
   private String modelId;

   @TableField("stake_no")
   private String stakeNo;

    @TableField("charge_port")
    private String chargePort;

    @TableField("server_type")
    private String serverType;

    @TableField("rel_time")
    private Date relTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
