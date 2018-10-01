package constant;

/**
 * 存放在redis中的指标
 */
public class RedisConstant {

    public static final String ORDER_MONITOR = "om";
    public static final String COLON = ":";
    //常用ip
    public static final String NORMAL_IP = ":normal:ip";
    //常用收货地址
    public static final String NORMAL_ADDRESSS = ":normal:address";
    //常用设备
    public static final String NORMAL_DEIVICE = ":normal:device";
    //是否更换了手机
    public static final String CHANGE_MOBILE = ":isChangeMoible";
    //近期是否更改了登录密码
    public static final String CHANGE_ACCOUNT_PASSWORD = ":isChaAccPsd";
    //近期是否更改了支付密码
    public static final String CHANGE_PAYMENT_PASSWORD = ":isChaPayPsd";
    //指定类目的商品价格是否满足阈值
    public static final String HIGH_VALUE_CATAGORY = ":catagory:highvalue";
}
