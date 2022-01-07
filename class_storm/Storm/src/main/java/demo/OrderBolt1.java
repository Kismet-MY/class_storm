package demo;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class OrderBolt1 extends BaseBasicBolt{

    public void execute(Tuple tuple, BasicOutputCollector collector) {
        // TODO Auto-generated method stub
        byte[]bytes=tuple.getBinary(0);  //字节码
        //byte->String

        String line =new String(bytes);
        String[] str=line.split(",");
        //String order_id =str[0];
        //String book_id=str[1];
        String book_name =str[2].replace("\"book_name\":","").replace("\"","");
        String book_type =str[3].replace("\"book_type\":","").replace("\"","");
        String book_num=str[4].replace("\"book_num\":","").replace("\"","");
        //String book_price=str[5];
        String book_press=str[6].replace("\"book_press\":","").replace("\"","");
        //String id=str[7];

        collector.emit(new Values(book_name,book_type,book_num,book_press));
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("book_name","book_type","book_num","book_press"));
    }

}
