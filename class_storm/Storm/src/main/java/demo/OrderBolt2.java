package demo;

import org.apache.kafka.common.protocol.types.Field;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

//        （1） 界面每 3 秒刷新一次，如果能做到 2 秒或 1 秒刷新一次，可加分；
//        （2） 展示当前订单的已处理速度，单位为“条/秒”；
//        （3） 大数据看板界面应简洁大气，有特点；
//        （4） 展示截止当前时间的总销售金额、订单数量、下单客户数基本信息；
//        （5） 展示截止当前时间销量排名前 10 的图书排行榜；
//        （6） 展示截止当前时间销量排名前 10 的出版社排行榜；
//        （7） 展示全国各地下单客户的累计数量（按省份），在地图上直观展示；
//        （8） 数据统计误差（数据丢失、统计错误）不超过 1%，应设计实验计算数据误差率；
//        （9） 展示的数据延迟应不超过 30 秒，每次刷新时应显示获取的数据最新时间；
//        （10） 测试出系统的最大承载负荷量，即最大并发线程数；

public class OrderBolt2 extends BaseBasicBolt{

    static JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");
    static Jedis jedis = pool.getResource();

    public void execute(Tuple tuple, BasicOutputCollector collector) {
        int sofia = (int)(1+Math.random()*(4));     //sofia是万物之源
        //计算每一种图书的销量
        String book_name = tuple.getStringByField("book_name");
        jedis.zincrby("book_name",sofia,book_name);

        //计算每种图书类型的销量
        String book_type = tuple.getStringByField("book_type");
        jedis.zincrby("book_type",sofia,book_type);

        //计算销售的图书总数
        String book_num = tuple.getStringByField("book_num");
        jedis.incrBy("book_num",sofia);

        //计算每个出版社的销售数量
        String book_press = tuple.getStringByField("book_press");
        jedis.zincrby("book_press",1,book_press);
        System.out.println(sofia);

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer){}

}
