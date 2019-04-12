/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_score_loggerKafkaConsumer.consumer;

import cn.wuhailong.webnote_score_loggerKafkaConsumer.service.ScoreService;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Properties;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Kafka消息消费者
 * @author Administrator
 */
@Component
public class KafKaConsumer {
    
    //此处是业务层接口，不是远程接口
    @Autowired
    private ScoreService scoreService;
    
    private ConsumerConfig consumerConfig;
    
    /**
     * 构造方法，配置消费者
     */
    public KafKaConsumer(){
        //消费者配置
        Properties props = new Properties();
        props.put("zookeeper.connect", "192.168.0.150:2181");
        props.put("zookeeper.connection.timeout.ms", "10000");
        String groupname = "group2";
        props.put("group.id", groupname);
        consumerConfig = new ConsumerConfig(props);
    }
    
    /**
     * 接收消息，处理
     */
    @Scheduled(cron="0 0 0 * * ?")     //定时任务，cron表达式，每天0点0分0秒执行
    public void getMessage() {
        //创建连接
        ConsumerConnector consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);
        
        //创建一个KafKa流。createMessageStreams是批量创建的
        HashMap<String, Integer> map = new HashMap<>();
        map.put("noteLogger", 1);
        Map<String, List<KafkaStream<byte[], byte[]>>> topicMessageStreams = consumerConnector.createMessageStreams(map);
        KafkaStream<byte[], byte[]> stream = topicMessageStreams.get("noteLogger").get(0);
        
        
        ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
        while(iterator.hasNext()){
            
            //获取消息文本
            MessageAndMetadata<byte[], byte[]> message = iterator.next();
            String messageText = new String(message.message());
            
            //截取userId 和 action
            int userIdStart = messageText.indexOf("userid:") + 7;
            int userIdEnd = messageText.indexOf("  action:");
            int actionStart = messageText.indexOf("action:") + 7;
            int actionEnd = messageText.indexOf("  params");
            if(actionEnd == -1){
                actionEnd = messageText.length();
            }
            Long userId = new Long(messageText.substring(userIdStart, userIdEnd));
            String action = messageText.substring(actionStart, actionEnd);
            
            //根据日志选择增加积分
            switch(action){
                case "addTextNote" : scoreService.addScore(userId, 4);break;
                case "updateTextNote" : scoreService.addScore(userId, 2);break;
                case "addImageNote" : scoreService.addScore(userId, 4);break;
                case "updateImageNote" : scoreService.addScore(userId, 2);break;
                case "addRichNote" : scoreService.addScore(userId, 4);break;
                case "updateRichNote" : scoreService.addScore(userId, 2);break;
                default:break;
            }
        }
        
        
    }
    
}
