import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import bolt.PaymentInfoParserBolt;
import bolt.Save2dbBolt;
import bolt.notifyMailBolt;
import bolt.notifySMSBolt;
import spout.RandomSpout;

public class OrderMonitorTopologyMain {

    public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException {
        TopologyBuilder topologyBuilder=new TopologyBuilder();
        topologyBuilder.setSpout("kafka-spout",new RandomSpout(),2);
        //paymentInfoParser-bolt主要负责将有问题的订单筛选出来，分别发送给负责通知的bolt和保存到数据库的bolt
        topologyBuilder.setBolt("paymentInfoParser-bolt",new PaymentInfoParserBolt(),3).shuffleGrouping("kafka-spout");
        //保存bolt
        topologyBuilder.setBolt("save2dbBolt",new Save2dbBolt(),2)
                .fieldsGrouping("paymentInfoParser-bolt",new Fields("orderId"))
                .fieldsGrouping("kafka-spout",new Fields("paymentInfo"));

        //以邮件的方式通知
        topologyBuilder.setBolt("notifyMailBolt",new notifyMailBolt(),1).shuffleGrouping("paymentInfoParser-bolt");
        //以短信的方式通知
        topologyBuilder.setBolt("notifySMSBolt",new notifySMSBolt(),1).shuffleGrouping("paymentInfoParser-bolt");


        //启动topolog的配置信息
        Config topologConf=new Config();
        //TOPOLOGY_DEBG(setDebug),当它被设置成true的话，storm会记录下每个组件所发射的每条消息
        //这在本地环境调试topolog很有用，但是在线上这么做的话会影响性能。
        //topologConf.setDebug(true);

        //storm的运行有两种模式：本地模式和分布式模式
        if(args!=null&&args.length>0){
            //定义你希望分配多少个工作进程给你来执行这个topology
            topologConf.setNumWorkers(2);
            //向集群提交topology
            StormSubmitter.submitTopologyWithProgressBar(args[0], topologConf, topologyBuilder.createTopology());
        }else{
            topologConf.setNumWorkers(2);
            LocalCluster cluster=new LocalCluster();
            cluster.submitTopology("order-mon",topologConf,topologyBuilder.createTopology());

        }
    }
}
