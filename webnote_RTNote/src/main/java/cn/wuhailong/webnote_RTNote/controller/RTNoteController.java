/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_RTNote.controller;

import cn.wuhailong.webnote_RTNote.domain.dto.Page;
import cn.wuhailong.webnote_RTNote.domain.pojo.RichNote;
import cn.wuhailong.webnote_RTNote.service.RichNoteService;
import cn.wuhailong.webnote_user.domain.pojo.User;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 富文本笔记请求
 * @author Administrator
 */
@Controller
@RequestMapping("/webnote/richNote")
public class RTNoteController {
    
    @Autowired
    private RichNoteService noteService;
    
    @RequestMapping("/list")
    public String list(HttpSession session, Page page, Model model,RichNote richNote, @ModelAttribute("msgSuccess")String msg){
        //获取session中的用户id
        User sessionUser = (User)session.getAttribute("sessionUser");
        Long userId = sessionUser.getId();
        
        //根据用户id查询出文本笔记的分页信息
        page = noteService.getMyNotesPage(userId, page);
        
        //判断是否有当前页，没有(int型默认初始化为0）或者错误，则置于1。
        int showPage = page.getShowPage();
        if(showPage < 1 || showPage > page.getTotalPage()){
            showPage = 1;
            page.setShowPage(showPage);
        }
        //根据分页信息查询
        List<RichNote> notes = noteService.getMyNotesByPage(userId, page);
        
        RichNote note = null;
        System.out.println(richNote.getId());
        //查询笔记
        if(richNote == null || richNote.getId() == null){
            if(notes.size() > 0){
                note= noteService.load(notes.get(0).getId());
            }
        }else{
            note = noteService.load(richNote.getId());
        }
        
        
        //输出结果，转发到列表页
        model.addAttribute("notes", notes);
        model.addAttribute("page", page);
        model.addAttribute("note", note);
        if(msg.length() > 5){
            model.addAttribute("msg","<script type=\"text/javascript\"> alert(\"" + msg + "\"); </script>");
        }
        
        return "list";
    }
    
    @RequestMapping("/addPre")
    public String addPre(){
        return "addPage";
    }
    
    @RequestMapping("/add")
    public String add(RichNote note, HttpSession session, RedirectAttributes redirectAttributes){
        
        User sessionUser = (User) session.getAttribute("sessionUser");
        note.setUserId(sessionUser.getId());
        
        noteService.addRichNote(note);
        
        redirectAttributes.addAttribute("msgSuccess", "成功新增富文本笔记:" + note.getNoteTitle()+"!!");
            
        return "redirect:/webnote/richNote/list";
    }
    
    @RequestMapping("/updatePre")
    public String updatePre(RichNote note, Model model){
        RichNote get = noteService.load(note.getId());
        
        model.addAttribute("note", get);
        
        return "updatePage";
    }
}
