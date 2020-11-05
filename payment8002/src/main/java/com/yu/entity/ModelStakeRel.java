package com.yu.entity;
import java.io.Serializable;
import java.util.Date;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/23 8:56 
 */
public class ModelStakeRel implements Serializable {
    private Integer id;
    private String serverType;
    private String modelId;
    private String stakeNo;
    private String chargePort;
    private Date relTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getStakeNo() {
        return stakeNo;
    }

    public void setStakeNo(String stakeNo) {
        this.stakeNo = stakeNo;
    }

    public String getChargePort() {
        return chargePort;
    }

    public void setChargePort(String chargePort) {
        this.chargePort = chargePort;
    }

    public Date getRelTime() {
        return relTime;
    }

    public void setRelTime(Date relTime) {
        this.relTime = relTime;
    }

    @Override
    public String toString() {
        return "ModelRule{" +
                "serverType='" + serverType + '\'' +
                ", modelId='" + modelId + '\'' +
                ", stakeNo='" + stakeNo + '\'' +
                ", chargePort='" + chargePort + '\'' +
                ", relTime=" + relTime +
                '}';
    }
}
