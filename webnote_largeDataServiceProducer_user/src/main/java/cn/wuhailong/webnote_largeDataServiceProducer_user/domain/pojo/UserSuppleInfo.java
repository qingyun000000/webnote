/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_largeDataServiceProducer_user.domain.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 用户扩展信息实体类
 * @author Administrator
 */
@Entity
public class UserSuppleInfo implements Serializable{
    
    private static final long serialVersionUID = 2001L;
    
    @Id
    @GeneratedValue
    private Long id;             //逻辑主键
    
    @Column(nullable = false, unique = true)
    private Long userId;         //所属用户
    
    @Column
    private String gender = "保密";       //性别
    
    @Column
    private Date birthday;     //生日
    
    @Column
    private String nation;       //民族
    
    @Column
    private String address;      //所在地
    
    @Column
    private String resume;       //个人简介
    
    @Column
    private String highestEducation;      //最高学历
    
    @Column
    private String highestEducationUniversity;        //最高学历毕业学校
    
    @Column
    private String secondEducation;       //第二学历
    
    @Column
    private String secondEducationUniversity;     //第二学历毕业学校
    
    @Column 
    private String interests;        //兴趣爱好
    
    @Column 
    private String imgUrl;           //用户头像路径
    
    @Column
    private String cellPhone;        //手机号

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getHighestEducation() {
        return highestEducation;
    }

    public void setHighestEducation(String highestEducation) {
        this.highestEducation = highestEducation;
    }

    public String getHighestEducationUniversity() {
        return highestEducationUniversity;
    }

    public void setHighestEducationUniversity(String highestEducationUniversity) {
        this.highestEducationUniversity = highestEducationUniversity;
    }

    public String getSecondEducation() {
        return secondEducation;
    }

    public void setSecondEducation(String secondEducation) {
        this.secondEducation = secondEducation;
    }

    public String getSecondEducationUniversity() {
        return secondEducationUniversity;
    }

    public void setSecondEducationUniversity(String secondEducationUniversity) {
        this.secondEducationUniversity = secondEducationUniversity;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }
    
    
    
    
}
