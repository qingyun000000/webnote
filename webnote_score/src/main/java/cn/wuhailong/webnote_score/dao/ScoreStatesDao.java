/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_score.dao;

import cn.wuhailong.webnote_score.domain.pojo.ScoreStates;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author Administrator
 */
public interface ScoreStatesDao extends JpaRepository<ScoreStates, Long>{
    ScoreStates findByUserId(Long userId);
    
}
