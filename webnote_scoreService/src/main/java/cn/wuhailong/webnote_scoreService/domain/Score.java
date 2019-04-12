/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_scoreService.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 积分实体类
 * @author Administrator
 */
@Entity
public class Score implements Serializable{
    
    private static final long serialVersionUID = 10001L;
    
    @Id
    @GeneratedValue
    private Long id;    //逻辑主键
    
    @Column(nullable = false, unique = true)
    private Long userId;      //用户id
    
    @Column(nullable = false)
    private int score;         //积分

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    
}
