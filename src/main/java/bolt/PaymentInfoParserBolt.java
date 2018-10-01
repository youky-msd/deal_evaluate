package bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import domain.PaymentInfo;
import utils.OrderMonitorHandler;

import java.util.List;
import java.util.Map;

/**
 * 负责将spout发过来的数据转换成订单对象
 * 并对其判断是否触发规则
 */
public class PaymentInfoParserBolt extends BaseBasicBolt {

    @Override
    public void execute(Tuple tuple, BasicOutputCollector collector) {

        //获取订单对象
        String line = tuple.getString(0);
        PaymentInfo paymentInfo=(PaymentInfo) OrderMonitorHandler.parseObj(line,PaymentInfo.class);

        //判断是否触发规则,返回触犯的ruleId
        List<String> triggerList = OrderMonitorHandler.trigger(paymentInfo);
        if(triggerList.size()>0){
            collector.emit(new Values(paymentInfo.getOrderId(),triggerList));
        }

        //定时更新规则信息
        OrderMonitorHandler.scheduleLoad();

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("orderId","triggerList"));

    }
}
