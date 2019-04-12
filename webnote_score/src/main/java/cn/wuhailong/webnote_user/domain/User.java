/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_user.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * 用户实体类
 * @author Administrator
 */
@Entity
public class User implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private Long id;                //逻辑主键
    
    @Column(nullable = false, unique = true)
    private String userName;        //用户名，账号
    
    @Column(nullable = false)
    private String password;        //密码
    
    @Column(nullable = false)
    private String email;           //邮箱
    
    @Column(nullable = true)
    private String nickName;        //昵称
    
    @Column(nullable = false)
    private Date regTime;         //注册时间
    
    @Transient
    private String password2;       //重复密码，不持久化
    
    @Transient
    private String passwordNew;     //新密码，不持久化

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }
    
    
    
    
    public User(){
        
    }

    public User(String userName, String password, String email, String nickName) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
    }
    
    
}