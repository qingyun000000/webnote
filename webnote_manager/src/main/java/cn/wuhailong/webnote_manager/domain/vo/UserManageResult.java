/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_manager.domain.vo;

/**
 * 用户查询结果封装
 * @author Administrator
 */
public class UserManageResult {
    private int totalUserNumber;
    private int menNumber;
    private int womenNumber;

    public int getTotalUserNumber() {
        return totalUserNumber;
    }

    public void setTotalUserNumber(int totalUserNumber) {
        this.totalUserNumber = totalUserNumber;
    }

    public int getMenNumber() {
        return menNumber;
    }

    public void setMenNumber(int menNumber) {
        this.menNumber = menNumber;
    }

    public int getWomenNumber() {
        return womenNumber;
    }

    public void setWomenNumber(int womenNumber) {
        this.womenNumber = womenNumber;
    }
    
    
    private Long id;                //逻辑主键
    private String userName;        //用户名，账号
    private String email;           //邮箱
    private String nickName;        //昵称
    private String regTime;         //注册时间
    private String gender;       //性别
    private String birthday;     //生日
    private String nation;       //民族
    private String address;      //所在地
    private String resume;       //个人简介
    private String highestEducation;      //最高学历
    private String highestEducationUniversity;        //最高学历毕业学校
    private String secondEducation;       //第二学历
    private String secondEducationUniversity;     //第二学历毕业学校
    private String interests;        //兴趣爱好
    private String imgUrl;           //用户头像路径
    private String cellPhone;        //手机号

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
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
