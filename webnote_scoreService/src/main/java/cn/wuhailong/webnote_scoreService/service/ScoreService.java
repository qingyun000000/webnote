/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_scoreService.service;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 积分业务层接口，提供远程方法调用（RMI）服务
 * @author Administrator
 */
public interface ScoreService extends Remote{
    
    /**
     * 获取用户积分
     * @param userId
     * @return
     * @throws RemoteException
     */
    public int getMyScore(Long userId) throws RemoteException;
    
    /**
     * 增加用户积分
     * @param userId
     * @param increment
     * @return
     * @throws RemoteException
     */
    public int addMyScore(Long userId, Long increment) throws RemoteException;
}
