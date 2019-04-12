package cn.wuhailong.webnote_score_loggerKafkaProducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling               //定时任务注解
public class WebnoteScoreLoggerKafkaProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebnoteScoreLoggerKafkaProducerApplication.class, args);
	}
}
