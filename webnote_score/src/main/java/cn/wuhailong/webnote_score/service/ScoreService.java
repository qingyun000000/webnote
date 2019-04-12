/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_score.service;

/**
 *
 * @author Administrator
 */
public interface ScoreService {

    public int getMyScore(Long userId);

    public String getMyLevel(Long userId);

    public void signIn(Long userId) throws Exception;
    
}
