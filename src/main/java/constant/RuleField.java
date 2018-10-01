package constant;

/**
 * 常见的指标
 */
public class RuleField {

    //用户是否在常用IP下单
    public static final String IS_NORMAL_IP = "IS_NORMAL_IP";
    //用户是否在常用设备上下单
    public static final String IS_NORMAL_DEVICE = "IS_NORMAL_DEVICE";
    //用户收货地址是否是常用收货地址
    public static final String IS_NORMAL_ADDRESS = "IS_NORMAL_ADDRESS";
    //用户收货手机号是否是常用手机号
    public static final String IS_NORMAL_MOBILE = "IS_NORMAL_MOBILE";


    //用户近期是否修改过登陆密码
    public static final String IS_CHANGE_ACCOUT_PASSWORD = "IS_CHANGE_ACCOUT_PASSWORD";
    //用户近期是否修改过支付密码
    public static final String IS_CHANGE_PAYMENT_PASSWORD = "IS_CHANGE_PAYMENT_PASSWORD";
    //用户近期是否修改过手机号码
    public static final String IS_CHANGE_MOBILE = "IS_CHANGE_MOBILE";


    //订单是否是货到付款
    public static final String IS_CASH_ON_DELIVERY = "IS_CASH_ON_DELIVERY";
    //订单中指定价格的商品数量是否满足阈值
    public static final String ORDER_PRODUCT_NUM_BY_PRICE = "ORDER_PRODUCT_NUM_BY_PRICE";
    //订单中指定类目的商品价格是否满足阈值
    public static final String ORDER_PROCUDT_NUM_BY_CATEGORY = "ORDER_PROCUDT_NUM_BY_CATEGORY";
    //订单的总价值
    public static final String ORDER_AMOUNT = "ORDER_AMOUNT";
}
