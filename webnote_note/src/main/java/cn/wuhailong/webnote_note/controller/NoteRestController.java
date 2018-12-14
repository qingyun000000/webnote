/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_note.controller;

import cn.wuhailong.webnote_note.service.ImageNoteService;
import cn.wuhailong.webnote_user.domain.pojo.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 笔记模块-异步请求处理
 * @author Administrator
 */
@RestController
@RequestMapping("/webnote/noteRest")
public class NoteRestController {
    
    /**
     * 上传文件的异步处理，用于预览
     * @param file
     * @param redirectAttributes
     * @param session
     * @return
     */
    @RequestMapping("/uploadImage")
    public String uploadImage(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, HttpSession session) {
        
        //判断文件类型
        String inputFileName = file.getOriginalFilename();
        boolean matches = Pattern.matches(".+(.JPEG|.jpeg|.JPG|.jpg|.png|.PNG|.BMP|.bmp|.gif|.GIF)$", inputFileName);
        if(!matches){
            return "errorType";
        }
        
        //判断文件大小
        System.out.println(file.getSize());
        if(file.getSize() > (800 * 1024)){
           return "BigSize";
        }
        
        //获取用户id
        User user = (User) session.getAttribute("sessionUser");
        
        //获取文件名，截取掉文件路径，得到文件名
        String filename = file.getOriginalFilename();
        int start = filename.lastIndexOf("\\");
        if( start >= 0){
            filename = filename.substring(start+1);
        }
        //根据用户id, 时间戳, 文件名生成新的保存文件名
        filename = user.getId() + "_"+ new Date().getTime() + "_" + filename;
        
        //获取保存路径
        String filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/image/imageUpload/";
        File targetfile = new File(filePath);
        if(targetfile.exists()) {
            targetfile.mkdirs();
        }
        
        //二进制流写入
        FileOutputStream out;
        try {
            out = new FileOutputStream(filePath + filename);
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(NoteRestController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return filename;
    }
}
