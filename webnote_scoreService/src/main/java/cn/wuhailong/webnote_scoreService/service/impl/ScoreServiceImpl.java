/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_scoreService.service.impl;

import cn.wuhailong.webnote_scoreService.SpringUtil;
import cn.wuhailong.webnote_scoreService.dao.ScoreDao;
import cn.wuhailong.webnote_scoreService.domain.Score;
import cn.wuhailong.webnote_scoreService.service.ScoreService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 积分业务层实现
 * @author Administrator
 */
//为配合RMI绑定，没有交由容器管理
public class ScoreServiceImpl extends UnicastRemoteObject implements ScoreService{
    
    private static final long serialVersionUID = 2L;

    private ScoreDaoProxy scoreDao;
    
    public ScoreServiceImpl() throws RemoteException{
        super();
        //构造方法中通过容器获取代理类，这里ScoreServiceImpl没有交容器管理，否则容器初始化过程中在此调用容器，会出错。
        scoreDao = SpringUtil.getBean(ScoreDaoProxy.class);
    }
    
    @Override
    @Transactional(rollbackOn = Exception.class) 
    public int getMyScore(Long userId)  throws RemoteException {
        Score score = scoreDao.findByUserId(userId);
        
        //没有查询到积分，返回一个负值
        if(score == null){
            return -9999;
        }
        
        //返回积分
        return score.getScore();
    }

    @Override
    @Transactional(rollbackOn = Exception.class) 
    public int addMyScore(Long userId, Long increment) throws RemoteException {
        Score score = scoreDao.findByUserId(userId);
        
        //没有查询到积分，返回一个负值
        if(score == null){
            return -9999;
        }
        
        //增加积分
        score.setScore((int) (score.getScore() + increment));
        scoreDao.save(score);
        
        //返回新积分
        return score.getScore();
    }

    
    
}
