/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_score.service.impl;

import cn.wuhailong.webnote_score.domain.pojo.ScoreStates;
import cn.wuhailong.webnote_score.dao.ScoreStatesDao;
import cn.wuhailong.webnote_score.service.ScoreService;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class ScoreServiceImpl implements ScoreService{
    
    @Autowired
    private ScoreStatesDao statesDao;
    
    private cn.wuhailong.webnote_scoreService.service.ScoreService scoreService;

    public ScoreServiceImpl() throws NotBoundException, MalformedURLException, RemoteException {
        this.scoreService = (cn.wuhailong.webnote_scoreService.service.ScoreService)Naming.lookup("rmi://localhost:8084/scoreService");
    }

    @Override
    public int getMyScore(Long userId) {
        int score = scoreService.getMyScore(userId);
        if(score <= 0){
            return 0;
        } 
        return score;
    }

    @Override
    public String getMyLevel(Long userId) {
        int score =  scoreService.getMyScore(userId);
        if(score <= 0){
            return "未开通积分服务";
        }
        if(score <= 100){
            return "初级会员";
        }
        if(score <= 500){
            return "资深会员";
        }
        if(score <= 2000){
            return "高级会员";
        }
        if(score >= 2000){
            return "顶级会员";
        }
        return "";
    }

    @Override
    public void signIn(Long userId) throws Exception{
        ScoreStates ss = statesDao.findByUserId(userId);
        if(ss == null){
            throw new Exception("还未开通积分服务");
        }
        if(org.apache.commons.lang3.time.DateUtils.isSameDay(ss.getPreSignInTime(), new Date())){
            throw new Exception("今日已经签到");
        }
        
        ss.setPreSignInTime(new Date());
        statesDao.save(ss);
        
        scoreService.addMyScore(userId, 2L);
    }
    
    
    
    
}
