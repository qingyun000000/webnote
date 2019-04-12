/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_score_loggerKafkaConsumer.service;

/**
 * 业务层接口
 * @author Administrator
 */
public interface ScoreService {

    /**
     * 新增积分
     * @param userId
     * @param i
     */
    public void addScore(Long userId, int i);
    
}
