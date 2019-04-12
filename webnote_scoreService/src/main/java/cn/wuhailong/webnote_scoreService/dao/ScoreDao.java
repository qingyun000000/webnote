/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_scoreService.dao;

import cn.wuhailong.webnote_scoreService.domain.Score;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 积分持久层接口，继承JpaRepository
 * @author Administrator
 */
public interface ScoreDao extends JpaRepository<Score, Long>{
    
    /**
     * 根据userId（用户Id)查询积分
     * @param userId
     * @return
     */
    Score findByUserId(long userId);
}
