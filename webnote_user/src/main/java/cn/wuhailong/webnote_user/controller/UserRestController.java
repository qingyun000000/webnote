/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_user.controller;

import cn.wuhailong.webnote_user.domain.pojo.User;
import cn.wuhailong.webnote_user.service.UserService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
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
 * 用户模块-异步请求
 * @author Administrator
 */
@RestController
@RequestMapping("/webnote/userRest")
public class UserRestController {
    
    //自动注入，用户业务类
    @Autowired
    private UserService userService;
    
    /**
     * 校验用户名（注册时，通过表示可以注册）
     * @param user
     * @return
     */
    @RequestMapping("/verUserName")
    public String verUserName(User user){
        
        //业务层判断
        Boolean result = userService.verUserName(user.getUserName());
        if(!result){
            //不通过
            return "用户名重复";
        }
        //通过
        return "success";
    }
    
    /**
     * 校验用户名（登录时，通过表示用户存在）
     * @param user
     * @return
     */
    @RequestMapping("/verLoginUserName")
    public String verLoginUserName(User user){
        
        //业务层判断
        Boolean result = userService.verLoginUserName(user.getUserName());
        if(!result){
            //不通过
            return "用户名不存在";
        }
        //通过
        return "success";
    }
    
    /**
     * 校验密码是否正确（修改手机号、修改密码、修改邮箱时）
     * @param session
     * @param user
     * @return
     */
    @RequestMapping("/verPassword")
    public String verPassword(HttpSession session, User user){
        
        //获取用户
        User sessionUser = (User) session.getAttribute("sessionUser");
        
        //业务层判断
        Boolean result = userService.verPassword(user.getPassword(), sessionUser.getId());
        if(!result){
            //不通过
            return "密码错误";
        }
        //通过
        return "success";
    }
    
    /**
     * 上传图片（异步，用于上传时预览）
     * @param file
     * @param redirectAttributes
     * @param session
     * @return
     */
    @RequestMapping("/uploadImage")
    public String uploadImage(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, HttpSession session){
        
        //判断文件类型
        String inputFileName = file.getOriginalFilename();
        boolean matches = Pattern.matches(".+(.JPEG|.jpeg|.JPG|.jpg)$", inputFileName);
        if(!matches){
            return "errorType";
        }
        
        //判断文件大小
        if(file.getSize() > (800 * 1024)){
            return "BigSize";
        }
        
        //获取用户名,用户生成文件名：用户id + 时间戳 + .jpg
        User user = (User) session.getAttribute("sessionUser");
        String fileName = user.getId() + new Date().getTime() + ".jpg";
        
        //获取文件保存路径
        String filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/image/userImageUpload/";
        
        //判断目录是否存在，不存在则新建
        File targetfile = new File(filePath);
        if(targetfile.exists()) {
            targetfile.mkdirs();
        }
        
        //二进制流写入文件
        FileOutputStream out;
        try {
            out = new FileOutputStream(filePath + fileName);
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(UserRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //返回文件名作为路径，用于前台异步更新显示
        return fileName;
    }
    
    
}
