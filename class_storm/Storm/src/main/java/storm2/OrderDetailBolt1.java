package storm2;// -*- codeing: utf-8 -*-


import kafka.utils.json.JsonObject;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class OrderDetailBolt1 extends BaseBasicBolt{

    public void execute(Tuple input, BasicOutputCollector collector) {
        // TODO Auto-generated method stub
        try{
            byte[]bytes=input.getBinary(0);  //字节码
            //byte->String
            String line =new String(bytes);
            String[] str=line.split(",");
//        String orderdetail_id =str[0].replace("\"orderdetail_id\":","").replace("\"","").replace("{data:[{","");
//        String orderid=str[1].replace("\"ordErid\":","").replace("\"","");
//        String sender =str[2].replace("\"sender\":","").replace("\"","");
//        String sendplace =str[3].replace("\"sendplace\":","").replace("\"","");
            String recipient=str[4].replace("\"recipient\":","").replace("\":\"","").replace("\"","");
            String receiveplace=str[5].replace("\"receiveplace\":","").replace("\"","").substring(0,2);
            String methodofpayment=str[6].replace("\"methodofpayment\":","").replace("\"","");
//        String paytime=str[7].replace("\"paytime\":","").replace("\"","");
//        String freight=str[8].replace("\"freight\":","").replace("\"","");
            String total=str[9].replace("\"total\":","").replace("\"","").replace("}]","");
            //发送数据，发给需要的bolt，后面的bolt按字段索引数据
            collector.emit(new Values(recipient,receiveplace,methodofpayment,total));
        }catch (Exception e){
//            e.printStackTrace();
            System.out.println("0000000000000000000000000000000000000000000000000000000000");
        }
        finally {
            System.out.println("1111111111111111111111111111111111111111111111111111111111");
        }


//        System.out.println(line);

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {

        /**
         * 描述我将要发送数据的字段是哪些
         */
        declarer.declare(new Fields("recipient","receiveplace",
                "methodofpayment","total"));
    }

}
