package cn.wuhailong.webnote_largeDataServiceProducer_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableDiscoveryClient        spring cloud 2.0 该注释可以取消，只要依赖jar包存在就可以加载
public class WebnoteLargeDataServiceProducerUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebnoteLargeDataServiceProducerUserApplication.class, args);
	}
}
