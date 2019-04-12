/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_scoreService;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 工具类，获取容器和容器中的Bean
 * @author Administrator
 */
@Component
public class SpringUtil implements ApplicationContextAware {
    
    private static ApplicationContext applicationContext; 

    @Override 
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringUtil.applicationContext == null) { 
            SpringUtil.applicationContext = applicationContext; 
        } 
    } 

    /**
     * 获取applicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext() { 
        return applicationContext; 
    } 

    /**
     * 通过name获取 Bean
     * @param name
     * @return
     */
    public static Object getBean(String name){ 
        return getApplicationContext().getBean(name); 
    } 

    /**
     * 通过class获取Bean
     * @param <T>
     * @param clazz
     * @return
     */
    public static <T> T getBean(Class<T> clazz){ 
        return getApplicationContext().getBean(clazz); 
    } 

    /**
     * 通过name,以及Clazz返回指定的Bean 
     * @param <T>
     * @param name
     * @param clazz
     * @return
     */
    public static <T> T getBean(String name,Class<T> clazz){ 
        return getApplicationContext().getBean(name, clazz); 
    } 
} 
