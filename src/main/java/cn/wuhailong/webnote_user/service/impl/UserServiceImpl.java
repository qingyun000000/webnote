/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_user.service.impl;


import cn.wuhailong.webnote_user.dao.UserDao;
import cn.wuhailong.webnote_user.dao.UserSuppleInfoDao;
import cn.wuhailong.webnote_user.domain.pojo.User;
import cn.wuhailong.webnote_user.domain.pojo.UserSuppleInfo;
import cn.wuhailong.webnote_user.exception.PasswordErrorException;
import cn.wuhailong.webnote_user.exception.UserExistException;
import cn.wuhailong.webnote_user.exception.UserNullException;
import cn.wuhailong.webnote_user.service.UserService;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务类实现
 * @author Administrator
 */
@Service
public class UserServiceImpl implements UserService{
    
    //自动注入用户持久层类
    @Autowired
    private UserDao userDao;
    
    //自动注入用户扩展信息持久层类
    @Autowired
    private UserSuppleInfoDao infoDao;

    @Override
    @Transactional(rollbackOn = Exception.class) 
    public void regist(User user) throws UserExistException {
        
        //检查用户名是否重复
        User existUser = userDao.findByUserName(user.getUserName());
        if(existUser != null){
            throw new UserExistException("账户已被注册");
        }
        
        //设置注册时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        user.setRegTime(sdf.format(new Date()));
        
        //保存用户
        User save = userDao.save(user);
        
        //新建用户扩展信息，保存
        UserSuppleInfo info = new UserSuppleInfo();
        info.setUserId(save.getId());
        infoDao.save(info);
    }

    @Override
    public User login(User user) throws UserNullException, PasswordErrorException {
        User existUser = userDao.findByUserName(user.getUserName());
        if(existUser == null){
            throw new UserNullException("账户不存在！！");
        }
        if(!user.getPassword().equals(existUser.getPassword())){
            throw new PasswordErrorException("密码错误！！");
        }
        return existUser;
        
    }

    @Override
    public Boolean verUserName(String userName) {
        User existUser = userDao.findByUserName(userName);
        if(existUser != null){
            return false;
        }
        return true;
    }
    
    @Override
    public Boolean verLoginUserName(String userName) {
        User existUser = userDao.findByUserName(userName);
        if(existUser == null){
            return false;
        }
        return true;
    }
    
    @Override
    public Boolean verPassword(String password, Long id) {
        User user = userDao.getOne(id);
        if(password.equals(user.getPassword())){
            return true;
        }
        return false;
    }

    @Override
    public UserSuppleInfo getUserInfo(Long userId) {
        return infoDao.findByUserId(userId);
    }

    @Override
    @Transactional(rollbackOn = Exception.class) 
    public void setImgUrl(String fileName, Long userId) throws UserNullException {
        //判断用户是否存在，密码是否正确
        User user = userDao.getOne(userId);
        if(user == null){
            throw new UserNullException("账户不存在！！");
        }
        
        //获取扩展信息类，为空则创建
        UserSuppleInfo info = infoDao.findByUserId(userId);
        if(info == null){
            info = new UserSuppleInfo();
            info.setUserId(userId);
        }
        
        //更新并保存
        info.setImgUrl(fileName);
        infoDao.save(info);
    }

    @Override
    @Transactional(rollbackOn = Exception.class) 
    public void setCellPhone(String cellPhone, Long userId, String password) throws PasswordErrorException, UserNullException{
        //判断用户是否存在，密码是否正确
        User user = userDao.getOne(userId);
        if(user == null){
            throw new UserNullException("账户不存在！！");
        }
        if(!user.getPassword().equals(password)){
            throw new PasswordErrorException("密码错误");
        }
        
        //获取扩展信息类，为空则创建
        UserSuppleInfo info = infoDao.findByUserId(userId);
        if(info == null){
            info = new UserSuppleInfo();
            info.setUserId(userId);
        }
        
        //更新并保存
        info.setCellPhone(cellPhone);
        infoDao.save(info);
    }
    
    @Override
    @Transactional(rollbackOn = Exception.class) 
    public void setEmail(String email, Long userId, String password) throws PasswordErrorException, UserNullException{
        //判断用户是否存在，密码是否正确
        User user = userDao.getOne(userId);
        if(user == null){
            throw new UserNullException("账户不存在！！");
        }
        if(!user.getPassword().equals(password)){
           throw new PasswordErrorException("密码错误");
        }
        
        //更新并保存
        user.setEmail(email);
        userDao.save(user);
    }

    @Override
    @Transactional(rollbackOn = Exception.class) 
    public void setPassword(Long userId, String password, String passwordNew) throws PasswordErrorException, UserNullException {
        
        //判断用户是否存在，密码是否正确
        User user = userDao.getOne(userId);
        if(user == null){
             throw new UserNullException("账户不存在！！");
         }
        if(!user.getPassword().equals(password)){
            throw new PasswordErrorException("密码错误");
        }
       
        //更新并保存
        user.setPassword(passwordNew);
        userDao.save(user);
       
    }

    @Override
    @Transactional(rollbackOn = Exception.class) 
    public void setSuppleInfo(UserSuppleInfo info, Long userId) throws UserNullException {
        
        //判断用户是否存在
        User user = userDao.getOne(userId);
        if(user == null){
            throw new UserNullException("账户不存在！！");
        }
        
        //获取扩展信息类，为空则创建
        UserSuppleInfo userInfo = infoDao.findByUserId(userId);
        if(userInfo == null){
            userInfo = new UserSuppleInfo();
            userInfo.setUserId(userId);
        }
        
        //更新并保存
        userInfo.setHighestEducation(info.getHighestEducation());
        userInfo.setHighestEducationUniversity(info.getHighestEducationUniversity());
        userInfo.setSecondEducation(info.getSecondEducation());
        userInfo.setSecondEducationUniversity(info.getSecondEducationUniversity());
        userInfo.setInterests(info.getInterests());
        infoDao.save(userInfo);
    }

    @Override
    @Transactional(rollbackOn = Exception.class) 
    public void setResume(String resume, Long userId) throws UserNullException {
        
        //判断用户是否存在
        User user = userDao.getOne(userId);
        if(user == null){
            throw new UserNullException("账户不存在！！");
        }
        
        //获取扩展信息类，为空则创建
        UserSuppleInfo userInfo = infoDao.findByUserId(userId);
        if(userInfo == null){
            userInfo = new UserSuppleInfo();
            userInfo.setUserId(userId);
        }
        
        //保存简介
        userInfo.setResume(resume);
        infoDao.save(userInfo);
    }

    @Override
    @Transactional(rollbackOn = Exception.class) 
    public void setUserBasicInfo(User user, UserSuppleInfo info, Long userId) throws UserNullException{
        
        //获取用户，判断
        User us= userDao.getOne(userId);
        if(us == null){
            throw new UserNullException("账户不存在！！");
        }
        
        //更新昵称
        us.setNickName(user.getNickName());
        userDao.save(us);
        
        //获取扩展信息类，为空则创建
        UserSuppleInfo userInfo = infoDao.findByUserId(userId);
        if(userInfo == null){
            userInfo = new UserSuppleInfo();
            userInfo.setUserId(userId);
        }
        
        //保存信息
        userInfo.setGender(info.getGender());
        userInfo.setBirthday(info.getBirthday());
        userInfo.setNation(info.getNation());
        userInfo.setAddress(info.getAddress());
        infoDao.save(userInfo);
    }
    
    
    
}
