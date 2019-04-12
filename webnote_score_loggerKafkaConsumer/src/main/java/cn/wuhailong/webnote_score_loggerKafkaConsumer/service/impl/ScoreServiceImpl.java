/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_score_loggerKafkaConsumer.service.impl;

import cn.wuhailong.webnote_score_loggerKafkaConsumer.service.ScoreService;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import org.springframework.stereotype.Service;

/**
 * 业务层实现
 * @author Administrator
 */
@Service
public class ScoreServiceImpl implements ScoreService{
    
    private cn.wuhailong.webnote_scoreService.service.ScoreService scoreService;

    /**
     * 在构造方法中绑定RMI服务
     * @throws NotBoundException
     * @throws MalformedURLException
     * @throws RemoteException
     */
    public ScoreServiceImpl() throws NotBoundException, MalformedURLException, RemoteException {
        this.scoreService = (cn.wuhailong.webnote_scoreService.service.ScoreService)Naming.lookup("rmi://localhost:8084/scoreService");
    }

    @Override
    public void addScore(Long userId, int i) {
        scoreService.addMyScore(userId, (long)i);
    }
    
    
    
}
