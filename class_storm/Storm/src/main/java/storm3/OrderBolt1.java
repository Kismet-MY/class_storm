package storm3;// -*- codeing: utf-8 -*-


import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class OrderBolt1 extends BaseBasicBolt{

    public void execute(Tuple input, BasicOutputCollector collector) {
        try {
            // TODO Auto-generated method stub
            byte[]bytes=input.getBinary(0);  //字节码
            //byte->String
            String line =new String(bytes);
            String[] str=line.split(",");
//        String order_id =str[0].replace("\"order_id\":","").replace("\"","").replace("{data:[{","");
//        String book_id=str[1].replace("\"book_id\":","").replace("\"","");
            String book_name =str[2].replace("\"book_name\":","").replace("\"","");
            String book_type =str[3].replace("\"book_type\":","").replace("\"","");
            String book_num=str[4].replace("\"book_num\":","").replace("\"","");
//        String book_price=str[5].replace("\"book_price\":","").replace("\"","");
            String book_press=str[6].replace("\"book_press\":","").replace("\"","");
//        String id=str[7].replace("\"id\":","").replace("\"","");

//        System.out.println(line);

            //发送数据，发给需要的bolt，后面的bolt按字段索引数据
            collector.emit(new Values(book_name,book_type,book_num,book_press));
        }catch (Exception e){
//            e.printStackTrace();
            System.out.println("0000000000000000000000000000000000000000000000000000000000");
        }
        finally {
            System.out.println("1111111111111111111111111111111111111111111111111111111111");
        }

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {

        /**
         * 描述我将要发送数据的字段是哪些
         */
        declarer.declare(new Fields("book_name","book_type","book_num", "book_press"));
    }

}
