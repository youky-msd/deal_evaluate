package utils;

import com.google.gson.Gson;
import dao.OrderMonitorDao;
import domain.Condition;
import domain.PaymentInfo;
import domain.RuleField;
import domain.Trigger;
import mail.MailInfo;
import mail.MessageSender;
import org.apache.log4j.Logger;
import sms.AliSms;

import java.util.*;


public class OrderMonitorHandler {

    private static Logger logger=Logger.getLogger(OrderMonitorHandler.class);

    //封装了rule规则，该规则有很多判断条件，key为releId(uuid),value为多个条件组成的list
    private static Map<String,List<Condition>> ruleMap;

    //定时加载配置文件标识
    private static boolean reloaded=false;

    //加载业务人员配置的所有规则信息，每个规则由多个条件组成
    static {
        loadRuleMap();
    }

    private static synchronized void loadRuleMap(){
        if(ruleMap==null||ruleMap.size()==0){
            ruleMap=loadRules();
        }
    }

    /**
     * 加载rule规则
     */
    private static Map<String,List<Condition>> loadRules(){
        Map<String,List<Condition>> map=new HashMap<>();
        List<Condition> conditionList=new OrderMonitorDao().loadRules();

        //加载所有rule规则以及属于它的所有条件condition
        for(Condition condition:conditionList){
            String ruleId=condition.getRuleId();
            List<Condition> conditionByRuleId=map.get(ruleId);
            if(conditionByRuleId==null||conditionByRuleId.size()==0){
                conditionByRuleId=new ArrayList<Condition>();
                map.put(ruleId,conditionByRuleId);
            }
            conditionByRuleId.add(condition);
        }

        return map;
    }

    /**
     * 定时加载管理员配置的规则信息
     */
    public static void scheduleLoad(){
        String date=DateUtils.getDateTime();
        int now=Integer.parseInt(date.split(":")[1]);
        if(now%10==0){//每10分钟加载一次
            reloadDataModel();

        }else {
            reloaded=true;
        }
    }

    public static synchronized void reloadDataModel(){
        if(reloaded){
            long start=System.currentTimeMillis();
            ruleMap=loadRules();
            reloaded=false;
            logger.info("配置文件reload完成，时间："+DateUtils.getDateTime()+"耗时："+(System.currentTimeMillis()-start));
        }
    }

    /**
     * 用于判断订单是否触发规则
     */
    public static List<String> trigger(PaymentInfo paymentInfo){

        //存放规则Id的集合
        List<String> triggerRuleIdList=new ArrayList<>();
        if(ruleMap==null||ruleMap.size()==0){
            loadRuleMap();
        }
        Set<String> ruleIds=ruleMap.keySet();
        for(String ruleId:ruleIds){
            //获得规则的所有条件
            List<Condition> conditionList=ruleMap.get(ruleId);
            boolean isTrigger = ConditionMatch.match(paymentInfo, conditionList);
            if(isTrigger){
                triggerRuleIdList.add(ruleId);
            }

        }

        return triggerRuleIdList;
    }

    /**
     * 将一个对象转换成json串
     * @param obj
     * @return
     */
    public static String parseString(Object obj){

        return new Gson().toJson(obj);

    }

    /**
     * 将json串转换为对象
     * @param line
     * @param cls
     * @return
     */
    public static Object parseObj(String line,Class cls){
        return new Gson().fromJson(line,cls);
    }

    /**
     * 将orderId和其对应的ruleId  一一实例化并保存起来
     * @param orderId
     * @param ruleIdList
     */
    public static void saveTrigger(String orderId,List<String> ruleIdList){

        List<Trigger> list=new ArrayList<>();
        for(String ruleId:ruleIdList){
            list.add(new Trigger(orderId,ruleId));
        }
        new OrderMonitorDao().saveTrigger(list);

    }

    /**
     * 将订单对象保存起来
     */
    public static void savePaymentInfo(PaymentInfo paymentInfo){
        new OrderMonitorDao().savePayment(paymentInfo);
    }

    /**
     * 以trigger对象为单位发送邮件
     * @param orderId
     * @param ruleIdList
     */
    public static void triggerNotifyMail(String orderId,List<String> ruleIdList){


        for(String ruleId:ruleIdList){
            if(sendMail(new Trigger(orderId,ruleId))){
                logger.info("邮件发送成功！！！");
            }
            else {
                logger.info("邮件发送失败！");
            }
        }


    }

    /**
     * 发送邮件
     *
     */

    private static boolean sendMail(Trigger trigger) {

        List<String> receiver = new ArrayList<String>();
        receiver.add("1019990958@qq.com");


        if (trigger !=null) {
            String date = DateUtils.getDateTime();
            String content = "订单编号为："+1+"触发了编号为："+1+"规则,请及时处理，是否允许用户下单。时间："+date;

            MailInfo mailInfo = new MailInfo("交易风控", content, receiver, null);
            return MessageSender.sendMail(mailInfo);
        }
        return false;
    }

    /**
     * 以trigger对象为单位发送邮件
     * @param orderId
     * @param ruleIdList
     */
    public static void triggerNotifySMS(String orderId,List<String> ruleIdList){


        for(String ruleId:ruleIdList){
            if(sendSMS(new Trigger(orderId,ruleId))){
                logger.info("短信发送成功！！！");
            }
            else {
                logger.info("短信发送成功！");
            }
        }


    }

    private static boolean sendSMS(Trigger trigger) {

        List<String> mobileList = new ArrayList<String>();
        mobileList.add("15222871082");

        String content=trigger.getOrderId()+","+trigger.getRuleId();;
        return AliSms.sendSms(listToStringFormat(mobileList), content);
    }
    /**
     * 将list转换为String
     * @param list
     * @return
     */
    private static String listToStringFormat(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                stringBuilder.append(list.get(i));
            } else {
                stringBuilder.append(list.get(i)).append(",");
            }
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {

        logger.info("hahah");
    }
}
