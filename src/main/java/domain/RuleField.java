package domain;

import java.io.Serializable;

/**
 * 针对订单的 判断条件对象
 */
public class RuleField implements Serializable {

    private int id;
    private String field; //根据那个字段进行判断
    private String desc;  //具体的业务逻辑是什么，比如是否货到付款

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "RuleField{" +
                "id=" + id +
                ", field='" + field + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
