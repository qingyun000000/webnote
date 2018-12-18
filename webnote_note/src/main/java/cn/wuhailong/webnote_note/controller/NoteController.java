/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_note.controller;


import cn.wuhailong.webnote_note.domain.pojo.ImageNote;
import cn.wuhailong.webnote_note.domain.pojo.Note;
import cn.wuhailong.webnote_note.domain.dto.Page;
import cn.wuhailong.webnote_note.exception.ImageNoteNullException;
import cn.wuhailong.webnote_note.exception.NoteNullException;
import cn.wuhailong.webnote_note.service.ImageNoteService;
import cn.wuhailong.webnote_user.domain.pojo.User;
import cn.wuhailong.webnote_note.service.NoteService;
import cn.wuhailong.webnote_note.tools.NoteVerTool;
import cn.wuhailong.webnote_note.tools.TextTool;
import cn.wuhailong.webnote_note.domain.vo.NoteResult;
import cn.wuhailong.webnote_note.exception.UserErrorException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 笔记模块请求处理
 * @author Administrator
 */
@Controller
@RequestMapping("/webnote/note")
public class NoteController {
    
    @Autowired
    private NoteService noteService;
    
    @Autowired
    private ImageNoteService imageService;
    
    /**
     * 笔记模块主页，显示文本笔记列表和心情图片列表（分页）
     * @param session
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String List(HttpSession session, Page page, Model model, @ModelAttribute("msgSuccess")String msg){
        
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
        List<Note> notes = noteService.getMyNotesByPage(userId, page);
        
        //根据用户id查询出心情图片的分页信息
        page = imageService.getMyNotesPage(userId, page);
        
        //判断是否有当前页，没有(int型默认初始化为0）或者错误，则置于1。
        int showPage_image = page.getShowPage_image();
        if(showPage_image < 1 || showPage_image > page.getTotalPage_image()){
            showPage_image = 1;
            page.setShowPage_image(showPage_image);
        }
        
        //根据分页信息查询
        List<ImageNote> imageNotes = imageService.getMyNotesByPage(userId,page);
        //倒序，让第一张在页面时为最后一张，放在最上层
        Collections.reverse(imageNotes);
        
        //输出结果，转发到列表页
        model.addAttribute("notes", notes);
        model.addAttribute("images", imageNotes);
        model.addAttribute("page", page);
        if(msg.length() > 5){
            model.addAttribute("msg","<script type=\"text/javascript\"> alert(\"" + msg + "\"); </script>");
        }
        
        return "list";
    }
    
    /**
     * 新增文本笔记页面
     * @return
     */
    @RequestMapping("/addPre")
    public String addPre(){
        return "addNote";
    }
    
    /**
     * 新增文本笔记
     * @param note
     * @param session
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/add")
    public String add(Note note, HttpSession session, Model model, RedirectAttributes redirectAttributes){
        
        NoteResult result = new NoteResult();
        
        NoteVerTool.VerNoteTitle(note.getNoteTitle(), result);
        NoteVerTool.VerContent(note.getContent(), result);
        
        if(result.getContentError() == null && result.getNoteTitleError() == null){
            
            
            //处理文本
            //note.setContent(TextTool.textProcess(note.getContent()));
            
            //补全用户id
            User sessionUser = (User)session.getAttribute("sessionUser");
            Long userId = sessionUser.getId();
            note.setUserId(userId);
            
            noteService.add(note);
            
            redirectAttributes.addAttribute("msgSuccess", "成功新增文本笔记:" + note.getNoteTitle()+"!!");
            
            return "redirect:/webnote/note/list";
            
        }
        
        //校验失败,回显
        result.setNoteTitle(note.getNoteTitle());
        result.setContent(note.getContent());
        model.addAttribute("result", result);
        return "addNote";
        
    }
    
    /**
     * 修改文本笔记页面
     * @param note
     * @param model
     * @return
     */
    @RequestMapping("/updatePre")
    public String updatePre(Note note, Model model){
        note = noteService.load(note.getId());
        model.addAttribute("note", note);
        return "updateNote";
    }
    
    /**
     * 修改文本笔记
     * @param note
     * @param session
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/update")
    public String update(Note note, HttpSession session, Model model, RedirectAttributes redirectAttributes){
        NoteResult result = new NoteResult();
        
        NoteVerTool.VerNoteTitle(note.getNoteTitle(), result);
        NoteVerTool.VerContent(note.getContent(), result);
        
        if(result.getContentError() == null && result.getNoteTitleError() == null){
            
            
            //处理文本
            //note.setContent(TextTool.textProcess(note.getContent()));
            
            //补全用户id
            User user = (User)session.getAttribute("sessionUser");
            
            try {
                noteService.update(note, user);
                redirectAttributes.addAttribute("msgSuccess", "成功修改文本笔记:" + note.getNoteTitle()+"!!");
                return "redirect:/webnote/note/list";
            } catch (NoteNullException | UserErrorException ex) {
                result.setNoteTitleError("无法删除文本笔记，因为:" +ex.getMessage());
            }
            
            
            
        }
        
        //校验失败,回显
        result.setNoteTitle(note.getNoteTitle());
        result.setContent(note.getContent());
        model.addAttribute("result", result);
        return "updateNote";
    }
    
    /**
     * 删除文本笔记
     * @param note
     * @param session
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/delete")
    public String delete(Note note, HttpSession session, RedirectAttributes redirectAttributes){
        User user = (User) session.getAttribute("sessionUser");
        try {
            noteService.delete(note, user);
            redirectAttributes.addAttribute("msgSuccess", "成功删除文本笔记:" + note.getNoteTitle()+"!!");
        } catch (NoteNullException | UserErrorException ex) {
            redirectAttributes.addAttribute("msgSuccess", "无法删除文本笔记，因为：" + ex.getMessage());
        }
        
        
        return "redirect:/webnote/note/list";
    }
    
    /**
     * 显示文本笔记
     * @param note
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/showNote")
    public String showNote(Note note, HttpSession session, Model model){
        
        note = noteService.load(note.getId());
        
        model.addAttribute("note", note);
        return "note";
    }
    
    /**
     * 新增心情图片页面
     * @return
     */
    @RequestMapping("/addImageNotePre")
    public String addImageNotePre(){
        return "addImageNote";
    }
    
    /**
     * 新增心情图片
     * @param file
     * @param model
     * @param redirectAttributes
     * @param session
     * @param imageNote
     * @return
     */
    @RequestMapping("/uploadImg")
    public String uploadImg(@RequestParam("file") MultipartFile file,Model model, RedirectAttributes redirectAttributes, HttpSession session, ImageNote imageNote){
        
        NoteResult result = new NoteResult();
        
        //校验描述
        NoteVerTool.VerDescription(imageNote.getDescription(), result);
        
        //判断文件类型
        String inputFileName = file.getOriginalFilename();
        boolean matches = Pattern.matches(".+(.JPEG|.jpeg|.JPG|.jpg|.png|.PNG|.BMP|.bmp|.gif|.GIF)$", inputFileName);
        if(!matches){
            result.setImageError("上传的图片格式错误");
        }
        
        //判断文件大小
        if(file.getSize() > (800 * 1024)){
           result.setImageError("上传的图片必须不超过800K");
        }
        
        if(result.getDescriptionError() == null && result.getImageError() == null){
            System.out.println("dee");
            try {
                User user = (User) session.getAttribute("sessionUser");

                String filename = file.getOriginalFilename();
                int start = filename.lastIndexOf("\\");
                 if( start >= 0){
                    filename = filename.substring(start+1);
                }
                filename = user.getId() + "_"+ new Date().getTime() + "_" + filename;

                imageNote.setImgUrl(filename);
                imageNote.setUserId(user.getId());
                imageService.addImageNote(imageNote);

                String filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/image/imageNote/";
                File targetfile = new File(filePath);
                if(targetfile.exists()) {
                    targetfile.mkdirs();
                }

                //二进制流写入
                FileOutputStream out = new FileOutputStream(filePath + filename);
                out.write(file.getBytes());
                out.flush();
                out.close();

                //保存成功，重定向回页面
                redirectAttributes.addAttribute("msgSuccess", "新增心情图片成功");
                return "redirect:/webnote/note/list";

            }catch (IOException ex) {
                //服务器保存失败，重定向回页面回显
                Logger.getLogger(NoteController.class.getName()).log(Level.SEVERE, null, ex);
                result.setImageError("服务器出现错误，保存失败，请重试。");
            }
        }
        
        //校验未通过或者保存失败，回显
        result.setDescription(imageNote.getDescription());
        model.addAttribute("result", result);
        return "addImageNote";
    }
    
    
    /**
     * 显示心情图片
     * @param imageNote
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/showImageNote")
    public String showImageNote(ImageNote imageNote, HttpSession session, Model model){
        
        imageNote = imageService.load(imageNote.getId());
        
        model.addAttribute("imageNote", imageNote);
        return "imageNote";
    }
    
    /**
     * 删除心情图片
     * @param imageNote
     * @param session
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/deleteImageNote")
    public String deleteImageNote(ImageNote imageNote, HttpSession session, RedirectAttributes redirectAttributes){
        User user = (User) session.getAttribute("sessionUser");
        try {
            imageService.delete(imageNote, user);
            redirectAttributes.addAttribute("msgSuccess", "成功删除心情图片!!");
        } catch (ImageNoteNullException | UserErrorException ex) {
            redirectAttributes.addAttribute("msgSuccess", "无法删除心情图片，因为：" +ex.getMessage());
        }
        
        return "redirect:/webnote/note/list";
    }
    
    /**
     * 修改心情图片页面
     * @param imageNote
     * @param model
     * @return
     */
    @RequestMapping("/updateImageNotePre")
    public String updateImageNotePre(ImageNote imageNote, Model model){
        imageNote = imageService.load(imageNote.getId());
        model.addAttribute("imageNote", imageNote);
        return "updateImageNote";
    }
    
    /**
     * 修改心情图片
     * @param file
     * @param model
     * @param redirectAttributes
     * @param session
     * @param imageNote
     * @return
     */
    @RequestMapping("/updateImageNote")
    public String updateImageNote(@RequestParam("file") MultipartFile file,Model model, RedirectAttributes redirectAttributes, HttpSession session, ImageNote imageNote){
        
        NoteResult result = new NoteResult();
        
        //校验描述
        NoteVerTool.VerDescription(imageNote.getDescription(), result);
        
        //如果上传文件存在，判断文件类型和大小
        if(!"".equals(file.getOriginalFilename())){
            String inputFileName = file.getOriginalFilename();
            boolean matches = Pattern.matches(".+(.JPEG|.jpeg|.JPG|.jpg|.png|.PNG|.BMP|.bmp|.gif|.GIF)$", inputFileName);
            if(!matches){
                result.setImageError("上传的图片格式错误");
            }

            if(file.getSize() > (800 * 1024)){
               result.setImageError("上传的图片必须不超过800K");
            }
        }
        
        if(result.getDescriptionError() == null && result.getImageError() == null){
            System.out.println("dee");
            try {
                User user = (User) session.getAttribute("sessionUser");
                
                //如果图片有更新
                if(!"".equals(file.getOriginalFilename())){
                    String filename = file.getOriginalFilename();
                    int start = filename.lastIndexOf("\\");
                     if( start >= 0){
                        filename = filename.substring(start+1);
                    }
                    filename = user.getId() + "_"+ new Date().getTime() + "_" + filename;

                    imageNote.setImgUrl(filename);
                    imageService.updateImageNote(imageNote, user);

                    String filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/image/imageNote/";
                    File targetfile = new File(filePath);
                    if(targetfile.exists()) {
                        targetfile.mkdirs();
                    }

                    //二进制流写入
                    FileOutputStream out = new FileOutputStream(filePath + filename);
                    out.write(file.getBytes());
                    out.flush();
                    out.close();
                }else{
                    //图片没有更新
                    imageService.updateImageNote(imageNote, user);
                }
                
                
                

                //保存成功，重定向回页面
                redirectAttributes.addAttribute("msgSuccess", "修改心情图片成功");
                return "redirect:/webnote/note/list";

            }catch (IOException ex) {
                //服务器保存失败，重定向回页面回显
                Logger.getLogger(NoteController.class.getName()).log(Level.SEVERE, null, ex);
                result.setImageError("服务器出现错误，保存失败，请重试。");
            } catch (ImageNoteNullException ex) {
                result.setImageError(ex.getMessage());
            } catch (UserErrorException ex) {
                result.setImageError(ex.getMessage());
            }
        }
        
        //校验未通过或者保存失败，回显
        result.setDescription(imageNote.getDescription());
        imageNote = imageService.load(imageNote.getId());
        model.addAttribute("imageNote", imageNote);
        model.addAttribute("result", result);
        return "updateImageNote";
    }
}
