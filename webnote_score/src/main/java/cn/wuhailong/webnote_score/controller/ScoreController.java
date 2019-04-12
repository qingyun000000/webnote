/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_score.controller;


import cn.wuhailong.webnote_score.service.ScoreService;
import cn.wuhailong.webnote_user.domain.User;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 积分模块
 * @author Administrator
 */
@Controller
@RequestMapping("/webnote/score")
public class ScoreController {
    
    @Autowired
    private ScoreService scoreService;
    
    @RequestMapping("/myscore")
    public String myScore(HttpSession session, Model model, @ModelAttribute("msg")String msg){
        User sessionUser = (User)session.getAttribute("sessionUser");
        
        Long userId = sessionUser.getId();
        int score = scoreService.getMyScore(userId);
        
        model.addAttribute("score", score);
        String level = scoreService.getMyLevel(userId);
        
        model.addAttribute("level", level);
        model.addAttribute("msg", msg);
        
        return "myscore";
    }
    
    @RequestMapping("/scoreInstruction")
    public String scoreInstruction(HttpSession session, Model model){
        User sessionUser = (User)session.getAttribute("sessionUser");
        
        Long userId = sessionUser.getId();
        int score = scoreService.getMyScore(userId);
        
        model.addAttribute("score", score);
        String level = scoreService.getMyLevel(userId);
        
        model.addAttribute("level", level);
        
        return "scoreInstruction";
    }
    
    @RequestMapping("/signIn")
    public String signIn(HttpSession session, Model model, RedirectAttributes redirectAttributes){
        User sessionUser = (User)session.getAttribute("sessionUser");
        
        try {
            scoreService.signIn(sessionUser.getId());
            redirectAttributes.addAttribute("msg","签到成功！！");
        } catch (Exception ex) {
            redirectAttributes.addAttribute("msg",ex.getMessage());
        }
        
        return "redirect:/webnote/score/myscore";
    }
}
