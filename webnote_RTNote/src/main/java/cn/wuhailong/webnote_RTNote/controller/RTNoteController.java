/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_RTNote.controller;

import cn.wuhailong.webnote_RTNote.domain.dto.Page;
import cn.wuhailong.webnote_RTNote.domain.pojo.RichNote;
import cn.wuhailong.webnote_RTNote.domain.vo.NoteResult;
import cn.wuhailong.webnote_RTNote.exception.RichNoteNullException;
import cn.wuhailong.webnote_RTNote.exception.UserErrorException;
import cn.wuhailong.webnote_RTNote.service.RichNoteService;
import cn.wuhailong.webnote_RTNote.tools.NoteVerTool;
import cn.wuhailong.webnote_user.domain.pojo.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    /**
     * 富文本主页，列表、显示笔记
     * @param session
     * @param page
     * @param model
     * @param richNote
     * @param msg
     * @return
     */
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
        
        //显示笔记
        RichNote note = null;
        //如果没有上传笔记id信息，查询分页的第一条笔记
        if(richNote == null || richNote.getId() == null){
            //数组不为空
            if(notes.size() > 0){
                note= noteService.load(notes.get(0).getId());
            }
            //数组为空，则说明该用户没有笔记，置空
        }
        //上传了笔记id信息，查询对应笔记
        else{
            note = noteService.load(richNote.getId());
        }
        
        
        //输出结果，转发到列表页
        model.addAttribute("notes", notes);
        model.addAttribute("page", page);
        model.addAttribute("note", note);
        
        //重定向过来的消息（新增、修改、删除成功，弹框显示）
        if(msg.length() > 5){
            model.addAttribute("msg","<script type=\"text/javascript\"> alert(\"" + msg + "\"); </script>");
        }
        
        return "list";
    }
    
    /**
     * 新增笔记页面
     * @return
     */
    @RequestMapping("/addPre")
    public String addPre(){
        return "addPage";
    }
    
    /**
     * 新增笔记
     * @param note
     * @param session
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/add")
    public String add(RichNote note, HttpSession session, Model model, RedirectAttributes redirectAttributes){
        
        //校验和回显结果
        NoteResult result = new NoteResult();
        
        //校验标题和内容
        NoteVerTool.VerNoteTitle(note.getNoteTitle(), result);
        NoteVerTool.VerContent(note.getContent(), result);
        
        //校验成功，无错误
        if(result.getContentError() == null && result.getNoteTitleError() == null){
            
            //补全用户信息
            User sessionUser = (User) session.getAttribute("sessionUser");
            note.setUserId(sessionUser.getId());
            
            //保存
            noteService.addRichNote(note);
        
            //重定向信息
            redirectAttributes.addAttribute("msgSuccess", "成功新增富文本笔记:" + note.getNoteTitle()+"!!");

            //重定向
            return "redirect:/webnote/richNote/list";
        }
        
        //校验失败，回显
        result.setNoteTitle(note.getNoteTitle());
        result.setContent(note.getContent());
        model.addAttribute("result", result);
        
        //前端在simditor初始化时，在jquery中判断result是否为空，再获取content, 会报错（if语句中使用内嵌js的[[${result != null}]]格式有问题，不能正常识别语句块）
        //此处在这里直接提供一个content值。
        //另外，simditor内容初始化的值，会和simditor的placeholder的值重叠显示（貌似placeholder需要在内容改变事件触发后看，才判断内容是否为空，并显示placeholder。
        //暂没有找到合适的解决方法，除非placeholder的值为空字符串。
        //这两个问题，在前端校验不被屏蔽的情况下，不需要回显，不会影响用户体验。
        model.addAttribute("content", note.getContent());
        return "addPage";
    }
    
    /**
     * 修改笔记页面
     * @param note
     * @param model
     * @return
     */
    @RequestMapping("/updatePre")
    public String updatePre(RichNote note, Model model){
        RichNote get = noteService.load(note.getId());
        
        model.addAttribute("note", get);
        
        return "updatePage";
    }
    
    @RequestMapping("/update")
    public String update(RichNote note, HttpSession session, Model model, RedirectAttributes redirectAttributes){
        //校验和回显结果
        NoteResult result = new NoteResult();
        
        //校验标题和内容
        NoteVerTool.VerNoteTitle(note.getNoteTitle(), result);
        NoteVerTool.VerContent(note.getContent(), result);
        
        //校验成功，无错误
        if(result.getContentError() == null && result.getNoteTitleError() == null){
            
            //获取用户信息
            User sessionUser = (User) session.getAttribute("sessionUser");
            
            try {
                //保存
                noteService.updateRichNote(note, sessionUser);
                
                //重定向信息
                redirectAttributes.addAttribute("msgSuccess", "成功修改富文本笔记:" + note.getNoteTitle()+"!!");
                
            } catch (RichNoteNullException | UserErrorException ex) {
                redirectAttributes.addFlashAttribute("msgSuccess", "无法修改文本笔记，因为：" + ex.getMessage());
            }
        
            //重定向
            return "redirect:/webnote/richNote/list";
        }
        
        //校验失败，回显
        result.setNoteTitle(note.getNoteTitle());
        result.setContent(note.getContent());
        model.addAttribute("result", result);
        
        //前端在simditor初始化时，在jquery中判断result是否为空，再获取content, 会报错（if语句中使用内嵌js的[[${result != null}]]格式有问题，不能正常识别语句块）
        //此处在这里直接提供一个content值。
        //另外，simditor内容初始化的值，会和simditor的placeholder的值重叠显示（貌似placeholder需要在内容改变事件触发后看，才判断内容是否为空，并显示placeholder。
        //暂没有找到合适的解决方法，除非placeholder的值为空字符串。
        //这两个问题，在前端校验不被屏蔽的情况下，不需要回显，不会影响用户体验。
        model.addAttribute("content", note.getContent());
        return "addPage";
    }
    
    
    /**
     * 删除笔记
     * @param note
     * @param session
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/delete")
    public String delete(RichNote note, HttpSession session, RedirectAttributes redirectAttributes){
        
        User sessionUser = (User) session.getAttribute("sessionUser");
        
        try {
            RichNote delete = noteService.delete(note, sessionUser);
            redirectAttributes.addAttribute("msgSuccess", "成功删除文本笔记:" + delete.getNoteTitle()+"!!");
        } catch (RichNoteNullException | UserErrorException ex) {
            redirectAttributes.addFlashAttribute("msgSuccess", "无法删除文本笔记，因为：" + ex.getMessage());
        }
        
         return "redirect:/webnote/richNote/list";
    }
}
