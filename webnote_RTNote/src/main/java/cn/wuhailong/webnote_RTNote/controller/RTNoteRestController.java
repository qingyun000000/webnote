/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_RTNote.controller;

import cn.wuhailong.webnote_user.domain.pojo.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 富文本笔记的异步请求
 * @author Administrator
 */
@RestController
public class RTNoteRestController {
    
    /**
     * 保存
     * @param file
     * @param session
     * @return
     */
    @RequestMapping("/uploadSimditorImg")
    public Map uploadSimditorImg(MultipartFile file, HttpSession session){
        
        //创建符合simditor要求的返回类型
        Map<String, Object> json = new HashMap<>();
        
        //判断文件类型
        String inputFileName = file.getOriginalFilename();
        boolean matches = Pattern.matches(".+(.JPEG|.jpeg|.JPG|.jpg|.png|.PNG|.BMP|.bmp|.gif|.GIF)$", inputFileName);
        if(!matches){
            json.put("success", false);
            json.put("msg", "上传图片类型错误！");
            return json;
        }
        
        //判断文件大小
        if(file.getSize() > (800 * 1024)){
            json.put("success", false);
            json.put("msg", "上传图片过大，不能超过800K！");
            return json;
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
            Logger.getLogger(RTNoteRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        json.put("success", true);
        json.put("file_path", "/image/imageUpload/" + filename);
        return json;

    }
}
