/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_scoreService.service.impl;

import cn.wuhailong.webnote_scoreService.dao.ScoreDao;
import cn.wuhailong.webnote_scoreService.domain.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 持久层代理类，用于自动按类型注入持久层Bean,解决业务层实现非容器管理，不能注入的问题
 * 该类只在包内可见，只允许业务层实现调用
 * @author Administrator
 */
@Service
class ScoreDaoProxy {
    
    @Autowired
    private ScoreDao scoreDao;

    Score findByUserId(Long userId) {
        return scoreDao.findByUserId(userId);
    }

    void save(Score score) {
        scoreDao.save(score);
    }
    
    
    
}
