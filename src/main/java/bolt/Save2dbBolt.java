package bolt;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;
import dao.OrderMonitorDao;
import domain.PaymentInfo;
import utils.OrderMonitorHandler;

import java.util.List;

public class Save2dbBolt extends BaseBasicBolt {
    @Override
    public void execute(Tuple tuple, BasicOutputCollector collector) {
        String firstField=tuple.getFields().get(0);
        if("orderId".equals(firstField)){
            OrderMonitorHandler.saveTrigger(tuple.getStringByField("orderId"),(List<String>)tuple.getValueByField("triggerList"));
        }
        if("paymentInfo".equals(firstField)){

            OrderMonitorHandler.savePaymentInfo((PaymentInfo) tuple.getValueByField("paymentInfo"));
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
