/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_note;

import cn.wuhailong.webnote_user.domain.pojo.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置
 * @author Administrator
 */
@Configuration
public class WebConfiguration {
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }
    
    @Bean
    public FilterRegistrationBean testFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }
    
    public class MyFilter implements Filter {
		@Override
		public void destroy() {
			// TODO Auto-generated method stub
		}

		@Override
		public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
				throws IOException, ServletException {
                        //获取请求路径
			HttpServletRequest request = (HttpServletRequest) srequest;
                        String requestURI = request.getRequestURI();
                        
			if(requestURI.startsWith("/image/") || requestURI.startsWith("/jquery/") || requestURI.startsWith("/css/") || requestURI.startsWith("static/")){
                            //静态资源请求，判断请求来源
                            
                            // 禁止缓存
                            //response.setHeader("Cache-Control", "no-store");
                            //response.setHeader("Pragrma", "no-cache");
                            //response.setDateHeader("Expires", 0);
                            
                            String referer = request.getHeader("referer");
                            if (referer == null || !referer.contains(request.getServerName())) {
                                HttpServletResponse response = (HttpServletResponse) sresponse;
                                
                                //重定向到错误显示图片
                                response.sendRedirect("/image/error.jpg");
                            }else{
                                 filterChain.doFilter(srequest, sresponse);
                            }
                            
                        }else if(requestURI.startsWith("/webnote/")){
                            
                            //判断是否登录
                            HttpSession session = request.getSession();
                            User user = (User) session.getAttribute("sessionUser");
                            if(user == null || user.getId() <= 0){
                                HttpServletResponse response = (HttpServletResponse) sresponse;
                                
                                //重定向到登录页
                                response.sendRedirect("http://localhost:8080/webnote/main");
                            }else{
                                filterChain.doFilter(srequest, sresponse);
                            }
                        }
                        
                        //非法请求，直接重倾向到登录页
                        else{
                            HttpServletResponse response = (HttpServletResponse) sresponse;
                            
                            //重定向到登录页
                            response.sendRedirect("http://localhost:8080/webnote/main");
                        }
		}

		@Override
		public void init(FilterConfig arg0) throws ServletException {
			// TODO Auto-generated method stub
		}
    }

}
