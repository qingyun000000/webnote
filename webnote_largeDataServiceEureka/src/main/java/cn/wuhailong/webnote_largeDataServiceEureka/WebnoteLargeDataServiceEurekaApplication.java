package cn.wuhailong.webnote_largeDataServiceEureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class WebnoteLargeDataServiceEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebnoteLargeDataServiceEurekaApplication.class, args);
	}
}
