package storm3;// -*- codeing: utf-8 -*-



import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;
import storm1.KafkaStormTopology;
import storm3.OrderBolt1;
import storm3.OrderBolt2;
import storm2.OrderDetailKafkaSourceSpout;

public class OrderTopology {

    public static void main(String[] args) {
        //定义拓扑结构
        TopologyBuilder builder=new TopologyBuilder();
        builder.setSpout("Spout", new OrderKafkaSourceSpout().createKafkSpout());
        builder.setBolt("Bolt1", new OrderBolt1()).shuffleGrouping("Spout");
        builder.setBolt("Bolt2", new OrderBolt2()).shuffleGrouping("Bolt1");

        // 使用builder构建topology
        StormTopology topology = builder.createTopology();
        String topologyName = KafkaStormTopology.class.getSimpleName();  // 拓扑的名称
        Config config = new Config();   // Config()对象继承自HashMap，但本身封装了一些基本的配置

        //提交
        LocalCluster localCluster=new LocalCluster();
        localCluster.submitTopology(topologyName, config,topology);
    }
}

