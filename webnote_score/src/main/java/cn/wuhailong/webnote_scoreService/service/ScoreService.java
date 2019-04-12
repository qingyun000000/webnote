/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_scoreService.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Administrator
 */
public interface ScoreService extends Remote{

    public int getMyScore(Long userId);

    public int addMyScore(Long userId, Long increment);
    
}
