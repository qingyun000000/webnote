/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_user.domain.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户类和用户扩展类校验结果表示层类，用于回显数据和错误信息
 * @author Administrator
 */
public class UserResult{
    private String userName;
    private String password;
    private String userNameError;
    private String passwordError;
    private String password2;
    private String password2Error;
    private String passwordNew;
    private String passwordNewError;
    private String nickName;
    private String nickNameError;
    private String email;
    private String emailError;
    private String cellPhone;
    private String cellPhoneError;
    
    private String gender;
    private Date birthday; 
    private String nation;
    private String address;
    private String resume;
    private String highestEducation;
    private String highestEducationUniversity;
    private String secondEducation;
    private String secondEducationUniversity;
    private String interests;
    private String genderError;
    private String birthdayError; 
    private String nationError;
    private String addressError;
    private String resumeError;
    private String highestEducationError;
    private String highestEducationUniversityError;
    private String secondEducationError;
    private String secondEducationUniversityError;
    private String interestsError;
    

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

    public String getUserNameError() {
        return userNameError;
    }

    public void setUserNameError(String userNameError) {
        this.userNameError = userNameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getPassword2Error() {
        return password2Error;
    }

    public void setPassword2Error(String password2Error) {
        this.password2Error = password2Error;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getNickNameError() {
        return nickNameError;
    }

    public void setNickNameError(String nickNameError) {
        this.nickNameError = nickNameError;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getCellPhoneError() {
        return cellPhoneError;
    }

    public void setCellPhoneError(String cellPhoneError) {
        this.cellPhoneError = cellPhoneError;
    }

    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }

    public String getPasswordNewError() {
        return passwordNewError;
    }

    public void setPasswordNewError(String passwordNewError) {
        this.passwordNewError = passwordNewError;
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

    public String getGenderError() {
        return genderError;
    }

    public void setGenderError(String genderError) {
        this.genderError = genderError;
    }

    public String getBirthdayError() {
        return birthdayError;
    }

    public void setBirthdayError(String birthdayError) {
        this.birthdayError = birthdayError;
    }

    public String getNationError() {
        return nationError;
    }

    public void setNationError(String nationError) {
        this.nationError = nationError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public String getResumeError() {
        return resumeError;
    }

    public void setResumeError(String resumeError) {
        this.resumeError = resumeError;
    }

    public String getHighestEducationError() {
        return highestEducationError;
    }

    public void setHighestEducationError(String highestEducationError) {
        this.highestEducationError = highestEducationError;
    }

    public String getHighestEducationUniversityError() {
        return highestEducationUniversityError;
    }

    public void setHighestEducationUniversityError(String highestEducationUniversityError) {
        this.highestEducationUniversityError = highestEducationUniversityError;
    }

    public String getSecondEducationError() {
        return secondEducationError;
    }

    public void setSecondEducationError(String secondEducationError) {
        this.secondEducationError = secondEducationError;
    }

    public String getSecondEducationUniversityError() {
        return secondEducationUniversityError;
    }

    public void setSecondEducationUniversityError(String secondEducationUniversityError) {
        this.secondEducationUniversityError = secondEducationUniversityError;
    }

    public String getInterestsError() {
        return interestsError;
    }

    public void setInterestsError(String interestsError) {
        this.interestsError = interestsError;
    }
    
   
}
