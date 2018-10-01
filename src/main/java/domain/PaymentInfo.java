package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单对象
 */
public class PaymentInfo implements Serializable{

    private String orderId;//订单编号
    private Date createOrderTime;//订单创建对象
    private String paymentId;//支付编号
    private Date paymentTime;//支付时间
    private String shopId;//商铺编号
    private String shopName;//商铺名称
    private String shopMobile;//商铺电话
    private String ip;//下单用户的ip
    private String user;//下订单的用户
    private String userMobile;//用户的手机号码
    private String address;//用户收货地址
    private String addressCode;//用户收货地址编码，省市县用|分隔，如123|1221|1212
    private String device;//下单用户的设备
    private String orderType;//订单的类型
    private List<Product> products;//订单中的所有商品
    private BigDecimal totalPrice;//订单支付金额



    public PaymentInfo() {
    }

    public PaymentInfo(String orderId, Date createOrderTime, String paymentId, Date paymentTime, String shopId, String shopName, String shopMobile, String ip, String user, String userMobile, String address, String addressCode, String device, String orderType, List<Product> products, BigDecimal totalPrice) {
        this.orderId = orderId;
        this.createOrderTime = createOrderTime;
        this.paymentId = paymentId;
        this.paymentTime = paymentTime;
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopMobile = shopMobile;
        this.ip = ip;
        this.user = user;
        this.userMobile = userMobile;
        this.address = address;
        this.addressCode = addressCode;
        this.device = device;
        this.orderType = orderType;
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateOrderTime() {
        return createOrderTime;
    }

    public void setCreateOrderTime(Date createOrderTime) {
        this.createOrderTime = createOrderTime;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopMobile() {
        return shopMobile;
    }

    public void setShopMobile(String shopMobile) {
        this.shopMobile = shopMobile;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "PaymentInfo{" +
                "orderId='" + orderId + '\'' +
                ", createOrderTime=" + createOrderTime +
                ", paymentId='" + paymentId + '\'' +
                ", paymentTime=" + paymentTime +
                ", shopId='" + shopId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopMobile='" + shopMobile + '\'' +
                ", ip='" + ip + '\'' +
                ", user='" + user + '\'' +
                ", userMobile='" + userMobile + '\'' +
                ", address='" + address + '\'' +
                ", addressCode='" + addressCode + '\'' +
                ", device='" + device + '\'' +
                ", orderType='" + orderType + '\'' +
                ", products=" + products +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
