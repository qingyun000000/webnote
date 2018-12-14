/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_manager.controller;

import cn.wuhailong.webnote_manager.domain.pojo.Manager;
import cn.wuhailong.webnote_manager.exception.ManagerNullException;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.wuhailong.webnote_manager.service.ManagerService;
import cn.wuhailong.webnote_manager.service.UserManageService;
import cn.wuhailong.webnote_manager.domain.vo.ManagerResult;
import cn.wuhailong.webnote_manager.domain.vo.UserManageResult;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.zookeeper.proto.SetSASLResponse;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 管理员管理模块
 * @author Administrator
 */
@Controller
@RequestMapping("/webnote/manager")
public class ManagerController {
    
    @Autowired
    private ManagerService managerService;
    
    @Autowired
    private UserManageService userManageService;
    
    /**
     * 登录页
     * @return
     */
    @RequestMapping("/main")
    public String main(){
        return "login";
    }
    
    /**
     * 登录
     * @param manager
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/managerLogin")
    public String Login(Manager manager, Model model, HttpSession session){
        try {
            Manager sessionManager = managerService.login(manager);
            session.setAttribute("sessionManager", sessionManager);
            session.setAttribute("sessionManagerName", sessionManager.getManagerName());
        } catch (ManagerNullException ex) {
            model.addAttribute("msg",ex.getMessage());
            return "login";
        }
        
        return "redirect:/webnote/manager/managerPage";
    }
    
    /**
     * 管理员主页
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/managerPage")
    public String managerPage(Model model, HttpSession session){
        int totalUserNumber = userManageService.getAllUserCount();
        int menNumber = userManageService.getCountByGender("男");
        int womenNumber = userManageService.getCountByGender("女");
        UserManageResult userResult = new UserManageResult();
        userResult.setTotalUserNumber(totalUserNumber);
        userResult.setMenNumber(menNumber);
        userResult.setWomenNumber(womenNumber);
        model.addAttribute("userResult", userResult);
        return "managerPage";
    }
    
    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/managerLogout")
    public String Logout(HttpSession session){
        session.removeAttribute("sessionManager");
        return "redirect:/webnote/manager/main";
    }
    
    
}
