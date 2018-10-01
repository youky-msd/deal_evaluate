package dao;

import backtype.storm.command.list;
import domain.Condition;
import domain.PaymentInfo;
import domain.Product;
import domain.Trigger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import utils.DataSourceUtil;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;



/**
 * 关于数据库的一些CRUD操作，这里采用的是spring的JdbcTemplate
 */
public class OrderMonitorDao {
    private JdbcTemplate jdbcTemplate;

    public OrderMonitorDao(){
        jdbcTemplate=new JdbcTemplate(DataSourceUtil.getDataSource());
    }

    /**
     * 加载所有规则
     * @return
     */
    public List<Condition> loadRules(){
        String sql="select id,name,ruleId,field,compare,value from order_monitor.condition_order_monitor";

        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Condition>(Condition.class));
    }

    /**
     * 保存触发orderId和ruleId所组成的Trigger对象
     *
     */
    public void saveTrigger(List<Trigger> list){
        String sql="insert into order_monitor.trigger_order_monitor (orderId,ruleId) " +
                "values (?,?)";

        jdbcTemplate.batchUpdate(sql, list, list.size(), new ParameterizedPreparedStatementSetter<Trigger>() {
            @Override
            public void setValues(PreparedStatement ps, Trigger trigger) throws SQLException {
                ps.setString(1, trigger.getOrderId());
                ps.setString(2, trigger.getRuleId());
            }
        });
    }



    public void savePayment(PaymentInfo paymentInfo) {
        savePaymentInfo(paymentInfo);

        saveProducts(paymentInfo.getOrderId(),paymentInfo.getProducts());

    }

    /**
     * 将订单信息保存起来
     * @param paymentInfo
     */
    private void savePaymentInfo(PaymentInfo paymentInfo) {

        String sql = "INSERT " +
                "INTO `order_monitor`.`paymentinfo_order_monitor` " +
                "(`orderId`,`createOrderTime`,`paymentId`,`paymentTime`," +
                "`shopId`,`shopName`,`shopMobile`,`ip`,`user`,`userMobile`," +
                "`address`,`addressCode`,`device`,`orderType`,`totalPrice`) " +
                "VALUES  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        jdbcTemplate.update(sql, paymentInfo.getOrderId(), paymentInfo.getCreateOrderTime(), paymentInfo.getPaymentId(), paymentInfo.getPaymentTime()
                , paymentInfo.getShopId(), paymentInfo.getShopName(), paymentInfo.getShopMobile(), paymentInfo.getIp(), paymentInfo.getUser(), paymentInfo.getUserMobile()
                , paymentInfo.getAddress(), paymentInfo.getAddressCode(), paymentInfo.getDevice(), paymentInfo.getOrderType(), paymentInfo.getTotalPrice());
    }

    /**
     * 将产品保存起来
     * @param orderId
     * @param products
     */
    private void saveProducts(final String orderId, List<Product> products) {

        String sql = "INSERT " +
                "INTO `order_monitor`.`products_order_monitor` (`orderId`,`id`,`name`,`price`,`catagory`,`promotion`,`num`) " +
                "VALUES (?,?,?,?,?,?,?) ";
        jdbcTemplate.batchUpdate(sql, products, products.size(), new ParameterizedPreparedStatementSetter<Product>() {
            @Override
            public void setValues(PreparedStatement ps, Product product) throws SQLException {
                ps.setString(1, orderId);
                ps.setString(2, product.getId());
                ps.setString(3, product.getName());
                ps.setBigDecimal(4, product.getPrice());
                ps.setString(5, product.getCategory());
                ps.setBigDecimal(6, product.getPromotion());
                ps.setInt(7, product.getNum());
            }
        });

    }

    public static void main(String[] args) {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setPaymentId("121212121");
        paymentInfo.setOrderId("1212121121222121");
        paymentInfo.setCreateOrderTime(new Date());
        paymentInfo.setPaymentTime(new Date());
        paymentInfo.setShopName("爱我中华");
        paymentInfo.setTotalPrice(new BigDecimal(1000));
        new OrderMonitorDao().savePayment(paymentInfo);
    }
}
