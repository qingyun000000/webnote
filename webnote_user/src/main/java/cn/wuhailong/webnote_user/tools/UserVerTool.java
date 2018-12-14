/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_user.tools;

import cn.wuhailong.webnote_user.domain.pojo.User;
import cn.wuhailong.webnote_user.domain.vo.UserResult;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 用户和用户扩展信息类校验工具
 * @author Administrator
 */
public class UserVerTool {
    /**
     * 输入校验——校验密码
     * @param user
     * @param result
     */
    public static void verPassword(User user, UserResult result) {
        //校验密码
        String password = user.getPassword();
        //正则表达式:满足6-20字母或者数字
        boolean matches = Pattern.matches("^[A-Za-z0-9]{6,20}$",password);
        if(!matches){
            result.setPasswordError("密码需为6-20位字母或数字");
        }
    }
    
    /**
     * 输入校验——校验新密码
     * @param user
     * @param result
     */
    public static void verPasswordNew(User user, UserResult result) {
        //校验密码
        String passwordNew = user.getPasswordNew();
        //正则表达式:满足6-20字母或者数字
        boolean matches = Pattern.matches("^[A-Za-z0-9]{6,20}$",passwordNew);
        if(!matches){
            result.setPasswordNewError("密码需为6-20位字母或数字");
        }
    }

    /**
     * 输入校验——校验用户名
     * @param user
     * @param result
     */
    public static void verUserName(User user, UserResult result) {
        //校验用户名
        String userName = user.getUserName();
        //正则表达式:满足6-20字母或者数字
        boolean matches = Pattern.matches("^[A-Za-z0-9]{6,20}$",userName);
        if(!matches){
            result.setUserNameError("账号需为6-20位字母或数字");
        }
    }
    
    /**
     * 校验昵称
     * @param user
     * @param result
     */
    public static void verNickName(User user, UserResult result) {
        //校验昵称
        String nickName = user.getNickName();
        //正则表达式:满足2-20汉字、字母或者数字
        boolean matches = Pattern.matches("^[A-Za-z0-9\\u2E80-\\u9FFF]{2,20}$",nickName);
        if(!matches){
            result.setNickNameError("需2-20个汉字、字母或数字");
        }
    }
    
    /**
     * 校验email
     * @param user
     * @param result
     */
    public static void verEmail(User user, UserResult result) {
        //校验email
        String email = user.getEmail();
        //正则表达式
        boolean matches = Pattern.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$",email);
        if(!matches){
            result.setEmailError("邮箱格式不正确");
        }
    }
    
    /**
     * 校验手机号
     * @param cellPhone
     * @param result
     */
    public static void verCellPhone(String cellPhone, UserResult result) {
        //正则表达式
        boolean matches = Pattern.matches("^((13[0-9])|(14[579])|(15[0-3,5-9])|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8}$",cellPhone);
        if(!matches){
            result.setCellPhoneError("手机号格式错误");
        }
    }
    
    /**
     * 校验最高学历
     * @param highestEducation
     * @param result
     */
    public static void verHighestEducation(String highestEducation, UserResult result) {
        //正则表达式
        boolean matches = Pattern.matches("^[A-Za-z0-9\\u2E80-\\u9FFF\\s]{0,30}$",highestEducation);
        if(!matches){
            result.setHighestEducationError("限30个汉字、字母、数字和空格");
        }
    }
    
    /**
     * 校验最高学历学校
     * @param highestEducationUniversity
     * @param result
     */
    public static void verHighestEducationUniversity(String highestEducationUniversity, UserResult result) {
        //正则表达式
        boolean matches = Pattern.matches("^[A-Za-z0-9\\u2E80-\\u9FFF\\s]{0,30}$",highestEducationUniversity);
        if(!matches){
            result.setHighestEducationUniversityError("限30个汉字、字母、数字和空格");
        }
    }
    
    /**
     * 校验第二学历
     * @param secondEducation
     * @param result
     */
    public static void verSecondEducation(String secondEducation, UserResult result) {
        //正则表达式
        boolean matches = Pattern.matches("^[A-Za-z0-9\\u2E80-\\u9FFF\\s]{0,30}$",secondEducation);
        if(!matches){
            result.setSecondEducationError("限30个汉字、字母、数字和空格");
        }
    }
    
    /**
     * 校验第二学历学校
     * @param secondEducationUniversity
     * @param result
     */
    public static void verSecondEducationUniversity(String secondEducationUniversity, UserResult result) {
        //正则表达式
        boolean matches = Pattern.matches("^[A-Za-z0-9\\u2E80-\\u9FFF\\s]{0,30}$",secondEducationUniversity);
        if(!matches){
            result.setSecondEducationUniversityError("限30个汉字、字母、数字和空格");
        }
    }
    
    /**
     * 校验兴趣爱好
     * @param interests
     * @param result
     */
    public static void verInterests(String interests, UserResult result) {
        //正则表达式
        boolean matches = Pattern.matches("^[A-Za-z0-9\\u2E80-\\u9FFF\\s]{0,30}$",interests);
        if(!matches){
            result.setInterestsError("限30个汉字、字母、数字和空格");
        }
    }
    
    /**
     * 校验个人简介
     * @param resume
     * @param result
     */
    public static void verResume(String resume, UserResult result) {
        //正则表达式
        boolean matches = Pattern.matches("^[A-Za-z0-9\\u2E80-\\u9FFF\\s\\.\\,\\/\\\\@\\#\\$%……&\\*!\\?\\p{P}]{0,150}$",resume);
        if(!matches){
            result.setResumeError("限150个汉字、字母、数字和标点");
        }
    }
    
    /**
     * 校验性别
     * @param gender
     * @param result
     */
    public static void verGender(String gender, UserResult result) {
        if(! ("男".equals(gender) || "女".equals(gender) || "保密".equals(gender)) ){
            result.setGenderError("性别信息错误");
        }
    }
    
    /**
     * 校验生日
     * @param result
     */
    public static void verBirthday(Date birthday, UserResult result) {
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(birthday);
        int year = c.get(Calendar.YEAR);
        if(birthday.after(now) || year < 1920 || year > 2020){
            result.setBirthdayError("生日时间范围错误");
        }
    }
    
    /**
     * 校验民族
     * @param nation
     * @param result
     */
    public static void verNation(String nation, UserResult result) {
        //正则表达式
        boolean matches = Pattern.matches("^[A-Za-z0-9\\u2E80-\\u9FFF]{0,15}$",nation);
        if(!matches){
            result.setNationError("限15个汉字、字母、数字");
        }
    }
    
    /**
     * 校验地址
     * @param address
     * @param result
     */
    public static void verAddress(String address, UserResult result) {
        //正则表达式
        boolean matches = Pattern.matches("^[A-Za-z0-9\\u2E80-\\u9FFF]{0,30}$",address);
        if(!matches){
            result.setAddressError("限30个汉字、字母、数字");
        }
    }
}
