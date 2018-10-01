package bolt;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;
import utils.OrderMonitorHandler;

import java.util.List;

public class notifyMailBolt extends BaseBasicBolt {
    @Override
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {

        String firstField=tuple.getFields().get(0);
        if("orderId".equals(firstField)) {
            OrderMonitorHandler.triggerNotifyMail(tuple.getStringByField("orderId"), (List<String>) tuple.getValueByField("triggerList"));
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
