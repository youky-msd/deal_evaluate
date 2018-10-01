package domain;

import java.io.Serializable;

/**
 * 触发了规则的的订单对象
 */
public class Trigger implements Serializable {

    private String orderId;//触发订单Id
    private String ruleId;//触发规则Id

    public Trigger() {
    }

    public Trigger(String orderId, String ruleId) {
        this.orderId = orderId;
        this.ruleId = ruleId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    @Override
    public String toString() {
        return "Trigger{" +
                "orderId='" + orderId + '\'' +
                ", ruleId='" + ruleId + '\'' +
                '}';
    }
}
