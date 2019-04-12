/*
* To change this license header, choose License Headers in Project Properties. * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_score_loggerKafkaProducer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * KafKa消息生产者
 * @author Administrator
 */
@Component
public class KafkaProducer {
    
    /**
     * 发送消息，定时任务
     */
    @Scheduled(cron="0 0 0 * * ?")     //定时任务，cron表达式，每天0点0分0秒执行
    public void sendMessage() {
        
        //配置Kafka消息生产者
        Properties props = new Properties();
        props.put("metadata.broker.list", "192.168.0.160:9092");
        ProducerConfig config = new ProducerConfig(props);
        
        //创建消费者
        Producer<byte[], byte[]> producer = new Producer<>(config);
        
        while(true){
            List<String> loggerList = getLogger();
            for(String logger : loggerList){
                byte[] messageContext = logger.getBytes();
                byte[] key = "noteMessage".getBytes();
                KeyedMessage<byte[], byte[]> message = new KeyedMessage<>("noteLogger", key, 1, messageContext);
                producer.send(message);
                System.out.println(message);
            }
            try {
                Thread.sleep(5555);
            } catch (InterruptedException ex) {
                Logger.getLogger(KafkaProducer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
    }
    
    /*
    * 读取日志文件，分析消息
    */
    private List<String> getLogger(){
        List<String> loggerList = new ArrayList<>();
        File file = new File("C:\\user\\local\\webnotelog\\spring.log");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file)); ;
            String tempString = null;  
            // 一次读入一行，直到读入null为文件结束  
            while ((tempString = reader.readLine()) != null) {  
                // 截取信息 
                if(tempString.contains("webnote_note.tools.NoteLoggerTool")){
                    int start = tempString.indexOf("userid");
                    tempString = tempString.substring(start);
                    loggerList.add(tempString);
                }
            }  
        }  catch (FileNotFoundException ex) {
            Logger.getLogger(KafkaProducer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(KafkaProducer.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e) {   }  
            }  
        } 
        
        return loggerList;
        
    }
    
}
