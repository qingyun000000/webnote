/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_user;

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
    public FilterRegistrationBean FilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LoginFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("LoginFilter");
        registration.setOrder(1);
        return registration;
    }
    
    public class LoginFilter implements Filter {
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
                        //以下请求放行：主页（登录页）、注册页、登录操作、注册操作、登录异步校验用户名、注册异步校验用户名、错误图片请求
                        if("/webnote/main".equals(requestURI)){
                            filterChain.doFilter(srequest, sresponse);
                        }else if("/webnote/user/login".equals(requestURI)){
                            filterChain.doFilter(srequest, sresponse);
                        }else if("/webnote/user/registPre".equals(requestURI)){
                            filterChain.doFilter(srequest, sresponse);
                        }else if("/webnote/user/regist".equals(requestURI)){
                            filterChain.doFilter(srequest, sresponse);
                        }else if("/webnote/userRest/verUserName".equals(requestURI)){
                            filterChain.doFilter(srequest, sresponse);
                        }else if("/webnote/userRest/verLoginUserName".equals(requestURI)){
                            filterChain.doFilter(srequest, sresponse);
                        }else if("/image/error.jpg".equals(requestURI)){
                            filterChain.doFilter(srequest, sresponse);
                        }
                        
                        //以下请求拦截判断
                        else if(requestURI.startsWith("/image/") || requestURI.startsWith("/jquery/") || requestURI.startsWith("/css/") || requestURI.startsWith("static/")){
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
                                response.sendRedirect("/webnote/main");
                            }else{
                                filterChain.doFilter(srequest, sresponse);
                            }
                        }
                        
                        //非法请求，直接重倾向到登录页
                        else{
                            HttpServletResponse response = (HttpServletResponse) sresponse;
                            
                            //重定向到登录页
                            response.sendRedirect("/webnote/main");
                        }
                            
		}

		@Override
		public void init(FilterConfig arg0) throws ServletException {
			// TODO Auto-generated method stub
		}
    }

}
