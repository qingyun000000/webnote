/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_score.domain.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



/**
 *
 * @author Administrator
 */
@Entity
public class ScoreStates implements Serializable{
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false, unique = true)
    private Long userId;
    
    @Column(nullable = false)
    private boolean scoreServerStart;
    
    @Column(nullable = true)
    private Date preSignInTime;

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

    public boolean isScoreServerStart() {
        return scoreServerStart;
    }

    public void setScoreServerStart(boolean scoreServerStart) {
        this.scoreServerStart = scoreServerStart;
    }

    public Date getPreSignInTime() {
        return preSignInTime;
    }

    public void setPreSignInTime(Date preSignInTime) {
        this.preSignInTime = preSignInTime;
    }
    
    

}
