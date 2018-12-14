/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * 主页
 * @author Administrator
 */
@Controller
@RequestMapping("/webnote")
public class MainController {
    
    /**
     * 主页（登录页）
     * @param msg
     * @param model
     * @return
     */
    @RequestMapping("/main")
    public String Main(@ModelAttribute("msg")String msg, Model model){
        if(msg == null || "".equals(msg)){
            msg = "欢迎登录网上笔记，请输入您的账号和密码进行登录";
        }
        model.addAttribute("message", msg);
        return "login";
    }
    
}
