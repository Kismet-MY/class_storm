package storm3;// -*- codeing: utf-8 -*-


//import org.apache.hadoop.hbase.client.Table;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class OrderBolt2 extends BaseBasicBolt{
    static Jedis jedis = new Jedis("127.0.0.1",6379);
    public void execute(Tuple input, BasicOutputCollector collector) {
        try {
            //        //图书销售数量
//        long book_num=input.getLongByField("book_num");
//        jedis.incrBy("total_book_num", book_num);
            //销量前十的图书名称
            String book_name=input.getStringByField("book_name");
            jedis.zincrby("10_book_name",1,book_name);
//        jedis.zrange("10_book_name",-1,-10);
            //销量前十出版社名称
            String book_press=input.getStringByField("book_press");
            jedis.zincrby("10_book_press",1,book_press);
//        jedis.zrange("10_book_press",-1,-10);
            //销量前十出版社
            String book_type=input.getStringByField("book_type");
            jedis.zincrby("10_book_type",1,book_type);
        }catch (Exception e){
//            e.printStackTrace();
            System.out.println("0000000000000000000000000000000000000000000000000000000000");
        }
        finally {
            System.out.println("1111111111111111111111111111111111111111111111111111111111");
        }
        //承接上一个bolt发送的数据，我只需要字段的数据







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
