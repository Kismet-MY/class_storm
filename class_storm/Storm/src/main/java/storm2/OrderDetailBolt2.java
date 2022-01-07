package storm2;// -*- codeing: utf-8 -*-


import java.io.IOException;

//import org.apache.hadoop.hbase.client.Table;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import redis.clients.jedis.Jedis;

public class OrderDetailBolt2 extends BaseBasicBolt{

    public void execute(Tuple input, BasicOutputCollector collector) {
        //承接上一个bolt发送的数据，我只需要字段的数据
        //1.得到total
//      //2,连接redis
        //3.将第一步得到的total往redis数据库某变量增量计算
        try{
            Jedis jedis = new Jedis("127.0.0.1",6379);
            //总销售金额
            String total=input.getStringByField("total");
            jedis.incrByFloat("total_money",Double.valueOf(total));
            //总销售总量
//        String orderid=input.getStringByField("orderid");
            jedis.incrBy("order_num",1);

            //计算各省份下单累计数量
            String receiveplace=input.getStringByField("receiveplace");
            jedis.zincrby("total_receiveplace",1,receiveplace);
            //统计支付方式
            String methodofpayment=input.getStringByField("methodofpayment");
            jedis.zincrby("total_methodofpayment",1,methodofpayment);
            //计算下单客户数
            String recipient = input.getStringByField("recipient");
            jedis.zincrby("recipient",1,recipient);
        }catch (Exception e){
//            e.printStackTrace();
            System.out.println("0000000000000000000000000000000000000000000000000000000000");
        }
        finally {
            System.out.println("1111111111111111111111111111111111111111111111111111111111");
        }







//        String data2 = input.getStringByField("orderdetail_id");
//        String data3 = input.getStringByField("sender");
//        System.out.println(data2);
//        System.out.println(data3);


//        Table table = HbaseUtil.getTable("sum_money_date");
//        try {
//            table.incrementColumnValue(("sum_money_"+date).getBytes(), "info".getBytes(), "summoney".getBytes(), price);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

}
