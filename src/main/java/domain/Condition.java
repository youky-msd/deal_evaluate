package domain;

import java.io.Serializable;

/**
 *用户配置的基于规则的基本条件
 */
public class Condition implements Serializable {

    private int id; //编号
    private String name; //条件的名称
    private String ruleId; //规则编号，由UUID生成
    private String field;  //规则验证所要比较的字段
    private String compare; //比较类型  1：等于 2：包含 3：大于 4：小于
    private String value;  //比较的值，同一类型的参数用逗号分隔，不同类型的参数用|分隔 如 1,2,3|a,b,c

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCompare() {
        return compare;
    }

    public void setCompare(String compare) {
        this.compare = compare;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Conition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ruleId='" + ruleId + '\'' +
                ", field='" + field + '\'' +
                ", compare='" + compare + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
