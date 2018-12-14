package cn.wuhailong.webnote_user;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 * Spring Boot启动类
 * @author Administrator
 */
@SpringBootApplication
public class WebnoteUserApplication {

    /**
     * 主方法，程序入口
     * @param args
     */
    public static void main(String[] args) {
        
        //启动Spring Boot
        SpringApplication.run(WebnoteUserApplication.class, args);
    }
        
    /**
     * 解决上传文件大于10M出现连接重置的问题。此异常内容GlobalException也捕获不到。
     * @return
     */
    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
                //-1 means unlimited
                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
            }
        });
        return tomcat;
    }

}
