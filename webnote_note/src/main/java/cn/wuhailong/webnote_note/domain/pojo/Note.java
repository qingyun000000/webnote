/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_note.domain.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 文本笔记实体类
 * @author Administrator
 */
@Entity
public class Note implements Serializable{
    private static final long serialVersionUID = 1001L;
    
    @Id
    @GeneratedValue
    private Long id;                    //逻辑主键
    
    @Column(nullable = false)
    private String noteTitle;           //笔记标题
    
    @Column(nullable = false)
    private String content;             //笔记内容
    
    @Column(nullable = false)
    private Date creatTime;             //创建时间
    
    @Column(nullable = true)
    private Date updateTime;            //修改时间
    
    @Column(nullable = false)
    private Long userId;                //所属用户

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    
    
}
