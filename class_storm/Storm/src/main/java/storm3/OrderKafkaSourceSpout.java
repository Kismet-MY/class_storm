package storm3;// -*- codeing: utf-8 -*-


import kafka.api.OffsetRequest;
import org.apache.storm.kafka.BrokerHosts;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.ZkHosts;

public class OrderKafkaSourceSpout {
//    private SpoutOutputCollector collector=null;
//    @Override
//    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
//        this.collector = spoutOutputCollector;
//    }
    public KafkaSpout createKafkSpout(){
        String brokerZkStr = "192.168.203.141:2181,192.168.203.142:2181,192.168.203.143:2181";
        BrokerHosts hosts = new ZkHosts(brokerZkStr);   // 通过zookeeper中的/brokers即可找到kafka的地址
        String topic = "Order1";
        String zkRoot = "/" + topic;
        String id = "consumer-id";
        SpoutConfig spoutConf = new SpoutConfig(hosts, topic, zkRoot, id);
        // 本地环境设置之后，也可以在zk中建立/f-k-s节点，在集群环境中，不用配置也可以在zk中建立/f-k-s节点
        //spoutConf.zkServers = Arrays.asList(new String[]{"uplooking01", "uplooking02", "uplooking03"});
        //spoutConf.zkPort = 2181;
        spoutConf.startOffsetTime = OffsetRequest.LatestTime(); // 设置之后，刚启动时就不会把之前的消费也进行读取，会从最新的偏移量开始读取
        KafkaSpout kafkaSpout = new KafkaSpout(spoutConf);
        return kafkaSpout;
    }
//    @Override
//    public void nextTuple() {
//        String brokerZkStr = "192.168.179.141:2181,192.168.179.142:2181,192.168.179.141:2181";
//        BrokerHosts hosts = new ZkHosts(brokerZkStr);   // 通过zookeeper中的/brokers即可找到kafka的地址
//        String topic = "example";
//        String zkRoot = "/" + topic;
//        String id = "consumer-id";
//        SpoutConfig spoutConf = new SpoutConfig(hosts, topic, zkRoot, id);
//        // 本地环境设置之后，也可以在zk中建立/f-k-s节点，在集群环境中，不用配置也可以在zk中建立/f-k-s节点
//        //spoutConf.zkServers = Arrays.asList(new String[]{"uplooking01", "uplooking02", "uplooking03"});
//        //spoutConf.zkPort = 2181;
//        spoutConf.startOffsetTime = OffsetRequest.LatestTime(); // 设置之后，刚启动时就不会把之前的消费也进行读取，会从最新的偏移量开始读取
//        KafkaSpout kafkaSpout = new KafkaSpout(spoutConf);
//        //创建一个消息处理的流，包含所有的topic
////        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
////        KafkaStream<byte[], byte[]> stream = consumerMap.get("order1").get(0);
////        ConsumerIterator<byte[], byte[]> it = stream.iterator();
//        kafkaSpout.activate();
//
//        this.collector.emit(new Values());
//        System.out.println("spout:" + this);
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
////        Utils.waitForMillis(1);
//    }
//    @Override
//    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
//        //告诉Storm该组件会发送哪些数据流，每个数据流的tuple中包含哪些字段。
//        outputFieldsDeclarer.declare(new Fields("order_id", "book_id", "book_name","book_type","book_num","book_price",
//                "book_press","id"));
//    }
}
