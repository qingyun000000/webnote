package cn.wuhailong.webnote_score_loggerKafkaConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling               //定时任务注解
public class WebnoteScoreLoggerKafkaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebnoteScoreLoggerKafkaConsumerApplication.class, args);
	}
}
