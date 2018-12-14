/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_user.service;

import cn.wuhailong.webnote_user.domain.pojo.User;
import cn.wuhailong.webnote_user.domain.pojo.UserSuppleInfo;
import cn.wuhailong.webnote_user.exception.PasswordErrorException;
import cn.wuhailong.webnote_user.exception.UserExistException;
import cn.wuhailong.webnote_user.exception.UserNullException;


/**
 * 用户业务类接口
 * @author Administrator
 */
public interface UserService {

    /**
     * 注册
     * @param user
     * @throws cn.wuhailong.webnote_user.exception.UserExistException
     */
    public void regist(User user) throws UserExistException;

    /**
     * 登录
     * @param user
     * @return
     * @throws UserNullException
     * @throws PasswordErrorException
     */
    public User login(User user) throws UserNullException, PasswordErrorException;

    /**
     * 校验用户名是否可用
     * @param userName
     * @return
     */
    public Boolean verUserName(String userName);

    /**
     * 校验用户名是否存在
     * @param userName
     * @return
     */
    public Boolean verLoginUserName(String userName);

    /**
     * 用户扩展信息
     * @param userId
     * @return
     */
    public UserSuppleInfo getUserInfo(Long userId);

    /**
     * 新增/修改用户图片
     * @param fileName
     * @param userId
     * @throws cn.wuhailong.webnote_user.exception.UserNullException
     */
    public void setImgUrl(String fileName, Long userId) throws UserNullException;

    /**
     * 新增/修改用户手机号
     * @param cellPhone
     * @param userId
     * @param password
     * @throws cn.wuhailong.webnote_user.exception.UserNullException
     * @throws cn.wuhailong.webnote_user.exception.PasswordErrorException
     */
    public void setCellPhone(String cellPhone, Long userId, String password) throws UserNullException, PasswordErrorException;

    /**
     * 修改邮箱
     * @param email
     * @param id
     * @param password
     * @throws cn.wuhailong.webnote_user.exception.UserNullException
     * @throws cn.wuhailong.webnote_user.exception.PasswordErrorException
     */
    public void setEmail(String email, Long id, String password) throws UserNullException, PasswordErrorException;

    /**
     * 修改密码
     * @param userId
     * @param password
     * @param passwordNew
     * @throws cn.wuhailong.webnote_user.exception.UserNullException
     * @throws cn.wuhailong.webnote_user.exception.PasswordErrorException
     */
    public void setPassword(Long userId, String password, String passwordNew) throws UserNullException, PasswordErrorException;

    /**
     * 修改用户补充信息：学历和兴趣爱好
     * @param info
     * @param userId
     * @throws cn.wuhailong.webnote_user.exception.UserNullException
     */
    public void setSuppleInfo(UserSuppleInfo info, Long userId) throws UserNullException;

    /**
     * 修改简介
     * @param resume
     * @param userId
     * @throws cn.wuhailong.webnote_user.exception.UserNullException
     */
    public void setResume(String resume, Long userId) throws UserNullException;

    /**
     * 修改昵称、性别、生日、民族、地址
     * @param user
     * @param info
     * @param userId
     * @throws cn.wuhailong.webnote_user.exception.UserNullException
     */
    public void setUserBasicInfo(User user, UserSuppleInfo info, Long userId) throws UserNullException;

    /**
     * 校验密码
     * @param password
     * @param id
     * @return
     */
    public Boolean verPassword(String password, Long id);
    
}
