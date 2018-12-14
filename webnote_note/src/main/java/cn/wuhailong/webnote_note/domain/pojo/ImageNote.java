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
 * 心情图片实体类
 * @author Administrator
 */
@Entity
public class ImageNote implements Serializable{
    private static final long serialVersionUID = 2001L;
    
    @Id
    @GeneratedValue
    private Long id;               //逻辑主键
    
    @Column(nullable = false)
    private String imgUrl;         //图片保存路径
    
    @Column(nullable = false)
    private String description;    //描述，图片说明，心情
    
    @Column(nullable = false)
    private Date creatTime;        //创建时间
    
    @Column(nullable = true)
    private Date updateTime;       //修改时间
    
    @Column(nullable = false)
    private Long userId;           //所属用户id

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
