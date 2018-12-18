/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_manager.controller;

import cn.wuhailong.webnote_manager.domain.pojo.User;
import cn.wuhailong.webnote_manager.domain.pojo.UserListPage;
import cn.wuhailong.webnote_manager.domain.pojo.UserSuppleInfo;
import cn.wuhailong.webnote_manager.domain.vo.UserManageResult;
import cn.wuhailong.webnote_manager.service.UserManageService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/webnote/userManageRest")
public class UserManageRestController {
    
    @Autowired
    private UserManageService userManageService;
    
    /**
     * 用户列表
     * @param session
     * @param gender
     * @param keyword
     * @param page
     * @return
     */
    @RequestMapping("/userList")
    public UserListPage userList(UserListPage page, @ModelAttribute("gender")String gender, @ModelAttribute("keyword")String keyword, HttpSession session){
        
        page = userManageService.getUserListPage(page, gender, keyword);

        //判断是否有当前页，没有(int型默认初始化为0）或者错误，则置于1。
        int showPage = page.getShowPage();
        if(showPage < 1 || showPage > page.getTotalPage()){
            showPage = 1;
            page.setShowPage(showPage);
        }
        List<User> userList = userManageService.getUserListByPage(page, gender, keyword);
        System.out.println("chaxyn" + userList.size());
        page.setUserList(userList);
        return page;
    }
    
    @RequestMapping("/userInfo")
    public UserManageResult userInfo(User user){
        User load = userManageService.loadUser(user.getId());
        UserSuppleInfo info = userManageService.loadUserSuppleInfo(user.getId());
        
        UserManageResult result = new UserManageResult();
        if(load != null){
            result.setId(load.getId());
            result.setUserName(load.getUserName());
            result.setNickName(load.getNickName());
            result.setEmail(load.getEmail());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            result.setRegTime(df.format(load.getRegTime()));
        }
        if(info != null){
            result.setGender(info.getGender());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            result.setBirthday(df.format(info.getBirthday()));
            result.setAddress(info.getAddress());
            result.setCellPhone(info.getCellPhone());
            result.setHighestEducation(info.getHighestEducation());
            result.setHighestEducationUniversity(info.getHighestEducationUniversity());
            result.setImgUrl(info.getImgUrl());                                     //图片来源未处理
            result.setInterests(info.getInterests());
            result.setNation(info.getNation());
            result.setResume(info.getResume());
            result.setSecondEducation(info.getSecondEducation());
            result.setSecondEducationUniversity(info.getSecondEducationUniversity());
        }
        
        return result;
        
    }
}
