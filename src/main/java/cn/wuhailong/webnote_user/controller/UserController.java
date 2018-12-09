/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_user.controller;

import cn.wuhailong.webnote_user.domain.pojo.User;
import cn.wuhailong.webnote_user.domain.pojo.UserSuppleInfo;
import cn.wuhailong.webnote_user.exception.PasswordErrorException;
import cn.wuhailong.webnote_user.exception.UserExistException;
import cn.wuhailong.webnote_user.exception.UserNullException;
import cn.wuhailong.webnote_user.service.UserService;
import cn.wuhailong.webnote_user.tools.UserVerTool;
import cn.wuhailong.webnote_user.domain.vo.UserResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
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
 * 用户模块
 * @author Administrator
 */
@Controller
@RequestMapping("/webnote/user")
public class UserController {
    
    //自动注入，用户业务类
    @Autowired
    private UserService userService;
    
    /**
     * 登录
     * @param user
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(User user,HttpSession session, Model model){
        //校验用户输入
        UserResult result = new UserResult();
        Boolean verResult = verUserLogin(user, result);
        
        //校验成功
        if(verResult){
            try {
                //登录，成功保存至session
                User sessionUser = userService.login(user);
                session.setAttribute("sessionUser", sessionUser);

                //成功, 重定向至笔记模块主页（笔记列表页）
                return "redirect:http://localhost:8081/webnote/note/list";
            } catch (UserNullException ex) {
                //捕获异常:用户不存在
                result.setUserNameError(ex.getMessage());
            } catch (PasswordErrorException ex) {
                //捕获异常：密码错误
                result.setPasswordError(ex.getMessage());
            }
        }
        
        //校验失败或者登录失败，回显数据
        result.setUserName(user.getUserName());
        result.setPassword(user.getPassword());
        model.addAttribute("result", result);
        return "login";
        
    }
    
    /*
    * 输入校验——用户登录信息校验
    */
    private Boolean verUserLogin(User user, UserResult result){
        
        //校验用户名
        UserVerTool.verUserName(user, result);
        
        //校验密码
        UserVerTool.verPassword(user, result);
        
        //判断结果，返回
        if(result.getUserNameError() == null && result.getPasswordError() == null){
            //校验通过
            return true;
        }
        //校验不通过
        return false;
        
    }

    
    /**
     * 退出登录
     * @param user
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/logout")
    public String logout(User user,HttpSession session, Model model){
        //删除session
        session.removeAttribute("sessionUser");
        
        //重定向至登录页
        return "redirect:/webnote/main";
    }
    
    /**
     * 注册页面
     * @return
     */
    @RequestMapping("/registPre")
    public String registPre(){
        
        //转向注册页面
        return "regist";
    }
    
    /**
     * 注册
     * @param user
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/regist")
    public String regist(User user, Model model, RedirectAttributes redirectAttributes){
        //校验用户输入
        UserResult result = new UserResult();
        Boolean verResult = verUserRegist(user, result);
        if(verResult){
            try {
                //调用业务层方法注册
                userService.regist(user);
                
                //注册成功，重定向主登录页（带消息）
                redirectAttributes.addAttribute("msg", "恭喜注册成功！！！！");
                return "redirect:/webnote/main";
            } catch (UserExistException ex) {
                result.setUserNameError(ex.getMessage());
            }
        }
        
        //校验失败或者注册失败，回显数据
        result.setUserName(user.getUserName());
        result.setNickName(user.getNickName());
        result.setEmail(user.getEmail());
        result.setPassword(user.getPassword());
        result.setPassword2(user.getPassword2());
        model.addAttribute("result", result);
        return "regist";
    }
    
    /*
    * 输入校验——用户注册信息校验
    */
    private Boolean verUserRegist(User user, UserResult result){
        
        //校验用户名
        UserVerTool.verUserName(user, result);
        
        //校验用昵称
        UserVerTool.verNickName(user, result);
        
        //校验邮箱
        UserVerTool.verEmail(user, result);
        
        //校验密码
        UserVerTool.verPassword(user, result);
        
        //校验两次密码是否一致
        if(!user.getPassword().equals(user.getPassword2())){
            result.setPassword2Error("两次密码输入不一致");
        }
        
        //判断结果，返回
        if(result.getUserNameError() == null && result.getNickNameError() == null && result.getEmailError()== null && result.getPasswordError() == null && result.getPassword2Error() == null){
            //校验通过
            return true;
        }
        //校验不通过
        return false;
        
    }
    
    /**
     * 用户信息页
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/userInfo")
    public String userInfo(HttpSession session, Model model){
        User user = (User) session.getAttribute("sessionUser");
        UserSuppleInfo userInfo = userService.getUserInfo(user.getId());
        model.addAttribute("info", userInfo);
        model.addAttribute("cellPhone", userInfo.getCellPhone().substring(0, 3) + "****" + userInfo.getCellPhone().substring(7, 11));
        return "userInfo";
    }
    
    /**
     * 用户信息编辑页-1：基本信息
     * @param session
     * @param model
     * @param msg
     * @return
     */
    @RequestMapping("userInfoEdit")
    public String userInfoEdit(HttpSession session, Model model, @ModelAttribute("msgSuccess") String msg){
        User user = (User) session.getAttribute("sessionUser");
        UserSuppleInfo userInfo = userService.getUserInfo(user.getId());
        model.addAttribute("info", userInfo);
        model.addAttribute("msgSuccess", msg);
        return "userInfoEdit_basic";
    }
    
    /**
     * 用户信息编辑页-2：个人介绍
     * @param session
     * @param model
     * @param msg
     * @return
     */
    @RequestMapping("userInfoEdit_2")
    public String userInfoEdit_2(HttpSession session, Model model, @ModelAttribute("msgSuccess") String msg){
        User user = (User) session.getAttribute("sessionUser");
        UserSuppleInfo userInfo = userService.getUserInfo(user.getId());
        model.addAttribute("info", userInfo);
        model.addAttribute("msgSuccess", msg);
        return "userInfoEdit_resume";
    }
    
    /**
     * 用户信息编辑页-3：详细信息：学历、爱好
     * @param session
     * @param model
     * @param msg
     * @return
     */
    @RequestMapping("userInfoEdit_3")
    public String userInfoEdit_3(HttpSession session, Model model, @ModelAttribute("msgSuccess") String msg){
        User user = (User) session.getAttribute("sessionUser");
        UserSuppleInfo userInfo = userService.getUserInfo(user.getId());
        model.addAttribute("info", userInfo);
        model.addAttribute("msgSuccess", msg);
        return "userInfoEdit_more";
    }
    
    /**
     * 用户信息编辑页-4：上传头像
     * @param session
     * @param model
     * @param msg
     * @param msgSuccess
     * @return
     */
    @RequestMapping("userInfoEdit_4")
    public String userInfoEdit_4(HttpSession session, Model model, @ModelAttribute("msg") String msg, @ModelAttribute("msgSuccess") String msgSuccess){
        User user = (User) session.getAttribute("sessionUser");
        UserSuppleInfo userInfo = userService.getUserInfo(user.getId());
        model.addAttribute("info", userInfo);
        model.addAttribute("msg",msg);
        model.addAttribute("msgSuccess", msgSuccess);
        return "userInfoEdit_userImage";
    }
    
    /**
     * 用户信息编辑页-5：修改密码
     * @param model
     * @param msg
     * @return
     */
    @RequestMapping("userInfoEdit_5")
    public String userInfoEdit_5(Model model, @ModelAttribute("msgSuccess") String msg){
        model.addAttribute("msgSuccess", msg);
        return "userInfoEdit_updatePassword";
    }
    
    /**
     * 用户信息编辑页-6：修改邮箱
     * @param model
     * @param msg
     * @return
     */
    @RequestMapping("userInfoEdit_6")
    public String userInfoEdit_6(Model model, @ModelAttribute("msgSuccess") String msg){
        model.addAttribute("msgSuccess", msg);
        return "userInfoEdit_updateEmail";
    }
    
    /**
     * 用户信息编辑页-7：修改手机号
     * @param session
     * @param model
     * @param msg
     * @return
     */
    @RequestMapping("userInfoEdit_7")
    public String userInfoEdit_7(HttpSession session, Model model,@ModelAttribute("msgSuccess") String msg){
        User user = (User) session.getAttribute("sessionUser");
        UserSuppleInfo userInfo = userService.getUserInfo(user.getId());
        model.addAttribute("info", userInfo);
        model.addAttribute("cellPhone", userInfo.getCellPhone().substring(0, 3) + "****" + userInfo.getCellPhone().substring(7, 11));
        model.addAttribute("msgSuccess", msg);
        
        return "userInfoEdit_updatePhone";
    }
    
    /**
     * 上传图片
     * @param file
     * @param session
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/uploadImg")
    public String uploadImg(@RequestParam("file") MultipartFile file, HttpSession session, RedirectAttributes redirectAttributes){
        
        
        //判断文件类型
        String inputFileName = file.getOriginalFilename();
        boolean matches = Pattern.matches(".+(.JPEG|.jpeg|.JPG|.jpg)$", inputFileName);
        if(!matches){
            redirectAttributes.addAttribute("msg", "上传的图片必须是JPG格式");
        }
        
        //判断文件大小
        if(file.getSize() > (800 * 1024)){
           redirectAttributes.addAttribute("msg", "上传的图片必须不超过800K");
        }
        
        try {
            //根据用户名生成文件名，保存路径到数据库
            User user = (User) session.getAttribute("sessionUser");
            String fileName = user.getUserName() + new Date().getTime()+ ".jpg";
            userService.setImgUrl(fileName, user.getId());

            //获取文件保存路径，保存文件
            String filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/image/userImage/";
            File targetfile = new File(filePath);
            if(targetfile.exists()) {
                targetfile.mkdirs();
            }				
            //二进制流写入
            FileOutputStream out = new FileOutputStream(filePath + fileName);
            out.write(file.getBytes());
            out.flush();
            out.close();
            
            
            //保存成功，重定向回页面
            redirectAttributes.addAttribute("msgSuccess", "保存用户头像成功!!");
            return "redirect:/webnote/user/userInfoEdit_4";
            
        } catch (IOException ex) {
            //服务器保存失败，重定向回页面回显
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            redirectAttributes.addAttribute("msg", "服务器出现错误，保存失败，请重试。");
        } catch (UserNullException ex) {
            //用户出现异常，重定向回登录页
            redirectAttributes.addAttribute("msg", "用户信息失效，保存失败，请重新登录。");
            return "redirect:/webnote/main";
        }
        
        return "redirect:/webnote/user/userInfoEdit_4";
    }
    
    /**
     * 修改手机号
     * @param session
     * @param user
     * @param info
     * @param redirectAttributes
     * @param model
     * @return
     */
    @RequestMapping("/updateCellPhone")
    public String updateCellPhone(HttpSession session, User user, UserSuppleInfo info, RedirectAttributes redirectAttributes, Model model){
        //获取sessionUser
        User sessionUser = (User) session.getAttribute("sessionUser");
        
        UserResult result = new UserResult();
        
        //校验密码格式
        UserVerTool.verPassword(user, result);
        //校验手机号
        UserVerTool.verCellPhone(info.getCellPhone(), result);
        
        //如果校验通过
        if(result.getPasswordError() == null && result.getCellPhoneError() == null){
            try {
                //修改用户手机号
                userService.setCellPhone(info.getCellPhone(), sessionUser.getId(), user.getPassword());
                
                //修改成功，重定向到页面
                redirectAttributes.addAttribute("msgSuccess", "修改手机号成功！！");
                return "redirect:/webnote/user/userInfoEdit_7";
                
            } catch (PasswordErrorException ex) {
                result.setPasswordError(ex.getMessage());
            } catch (UserNullException ex) {
                //用户出现异常，重定向回登录页
                redirectAttributes.addAttribute("msg", "用户信息失效，保存失败，请重新登录。");
                return "redirect:/webnote/main";
            }
        }
        
        //校验不通过或者修改失败，重定向到页面回显，因为redirectAttributes无法传递对象，这里没有采用重定向
        result.setPassword(user.getPassword());
        result.setCellPhone(info.getCellPhone());
        model.addAttribute("result", result);
        
        UserSuppleInfo userInfo = userService.getUserInfo(sessionUser.getId());
        model.addAttribute("info", userInfo);
        model.addAttribute("cellPhone", userInfo.getCellPhone().substring(0, 3) + "****" + userInfo.getCellPhone().substring(7, 11));
        
        return "userInfoEdit_updatePhone";
    }
    
    /**
     * 修改邮箱地址
     * @param session
     * @param user
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/updateEmail")
    public String updateEmail(HttpSession session, User user, Model model, RedirectAttributes redirectAttributes){
        
        //获取sessionUser
        User sessionUser = (User) session.getAttribute("sessionUser");
        
        UserResult result = new UserResult();
        
        //校验密码格式
        UserVerTool.verPassword(user, result);
        
        //校验邮箱格式
        UserVerTool.verEmail(user, result);
        
        //如果校验通过
        if(result.getPasswordError() == null && result.getEmailError() == null){
            try {
                //修改邮箱
                userService.setEmail(user.getEmail(), sessionUser.getId(), user.getPassword());
                
                //刷新sessionUser
                try {
                    User  newUser = userService.login(sessionUser);
                    session.setAttribute("sessionUser", newUser);
                } catch (PasswordErrorException | UserNullException ex) {
                    //这里的异常正常情况都不会出现，除非正在此时被删除用户或者修改密码，因此直接跳转至登录页以便用户重新登陆
                    redirectAttributes.addAttribute("msg", "用户信息失效，保存失败，请重新登录。");
                    return "redirect:/webnote/main";
                }
                
                //修改成功，重定向到页面
                redirectAttributes.addAttribute("msgSuccess", "修改邮箱地址成功！！");
                return "redirect:/webnote/user/userInfoEdit_6";
            } catch (PasswordErrorException ex) {
                //异常
                result.setPasswordError(ex.getMessage());
            } catch (UserNullException ex) {
                //用户出现异常，重定向回登录页
                redirectAttributes.addAttribute("msg", "用户信息失效，保存失败，请重新登录。");
                return "redirect:/webnote/main";
            }
        }
        
        //校验不通过或者修改失败，重定向到页面回显，因为redirectAttributes无法传递对象，这里没有采用重定向
        result.setPassword(user.getPassword());
        result.setEmail(user.getEmail());
        model.addAttribute("result", result);
        
        return "userInfoEdit_updateEmail";
    }
    
    /**
     * 修改密码
     * @param session
     * @param user
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/updatePassword")
    public String updatePassword(HttpSession session, User user, Model model, RedirectAttributes redirectAttributes){
        
        //获取sessionUser
        User sessionUser = (User) session.getAttribute("sessionUser");
        
        UserResult result = new UserResult();
        
        //校验密码格式
        UserVerTool.verPassword(user, result);
        
        //校验新密码格式
        UserVerTool.verPasswordNew(user, result);
        
        //校验两次密码是否一致
        if(!user.getPasswordNew().equals(user.getPassword2())){
            result.setPassword2Error("两次密码输入不一致");
        }
        
        //如果校验通过
        if(result.getPasswordError() == null && result.getPasswordNewError() == null && result.getPassword2Error() == null){
            try {
                //修改密码
                userService.setPassword(sessionUser.getId(), user.getPassword(), user.getPasswordNew());
                
                //刷新sessionUser
                try {
                    //更新密码，重新登录
                    sessionUser.setPassword(user.getPasswordNew());
                    User newUser = userService.login(sessionUser);
                    session.setAttribute("sessionUser", newUser);
                } catch (PasswordErrorException | UserNullException ex) {
                    //这里的异常正常情况都不会出现，除非正在此时被删除用户或者修改密码，因此直接跳转至登录页以便用户重新登陆
                    redirectAttributes.addAttribute("msg", "用户信息失效，保存失败，请重新登录。");
                    return "redirect:/webnote/main";
                }
                
                //修改成功，重定向到页面
                redirectAttributes.addAttribute("msgSuccess", "修改用户密码成功！！");
                return "redirect:/webnote/user/userInfoEdit_5";
                
            } catch (PasswordErrorException ex) {
                result.setPasswordError(ex.getMessage());
            } catch (UserNullException ex) {
                //用户出现异常，重定向回登录页
                redirectAttributes.addAttribute("msg", "用户信息失效，保存失败，请重新登录。");
                return "redirect:/webnote/main";
            }
        }

        //校验不通过或者修改失败，重定向到页面回显，因为redirectAttributes无法传递对象，这里没有采用重定向
        result.setPassword(user.getPassword());
        result.setPasswordNew(user.getPasswordNew());
        result.setPassword2(user.getPassword2());
        model.addAttribute("result", result);
        
        return "userInfoEdit_updatePassword";
    }
    
    /**
     * 修改补充信息
     * @param session
     * @param info
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/updateMoreInfo")
    public String updateMoreInfo(HttpSession session, UserSuppleInfo info, Model model, RedirectAttributes redirectAttributes){
        
        //获取sessionUser
        User sessionUser = (User) session.getAttribute("sessionUser");
        
        UserResult result = new UserResult();
        
        //校验用户输入
        Boolean verResult  = verMoreInfo(info, result);
        
        //校验通过
        if(verResult){
            try {
                //保存修改
                userService.setSuppleInfo(info, sessionUser.getId());
                
                //修改成功，重定向到页面
                redirectAttributes.addAttribute("msgSuccess", "保存信息成功！！");
                return "redirect:/webnote/user/userInfoEdit_3";
            } catch (UserNullException ex) {
                //用户出现异常，重定向回登录页
                redirectAttributes.addAttribute("msg", "用户信息失效，保存失败，请重新登录。");
                return "redirect:/webnote/main";
            }
            
        }

        //校验不通过或者修改失败，重定向到页面回显，因为redirectAttributes无法传递对象，这里没有采用重定向
        result.setHighestEducation(info.getHighestEducation());
        result.setHighestEducationUniversity(info.getHighestEducationUniversity());
        result.setSecondEducation(info.getSecondEducation());
        result.setSecondEducationUniversity(info.getSecondEducationUniversity());
        result.setInterests(info.getInterests());
        model.addAttribute("result", result);
        
        return "userInfoEdit_more";
    }
    
    /*
    * 校验补充信息
    */
    private Boolean verMoreInfo(UserSuppleInfo info, UserResult result) {
        
        //校验
        UserVerTool.verHighestEducation(info.getHighestEducation(), result);
        UserVerTool.verHighestEducationUniversity(info.getHighestEducationUniversity(), result);
        UserVerTool.verSecondEducation(info.getSecondEducation(), result);
        UserVerTool.verSecondEducationUniversity(info.getSecondEducationUniversity(), result);
        UserVerTool.verInterests(info.getInterests(), result);
        
        //判断，返回结果
        if(result.getHighestEducationError() == null && result.getHighestEducationUniversityError() == null 
                && result.getSecondEducationError() == null && result.getSecondEducationUniversityError() == null
                && result.getInterestsError() == null){
            return true;
        }
        return false;
    }
    
    /**
     * 修改个人介绍
     * @param session
     * @param info
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/updateResume")
    public String updateResume(HttpSession session, UserSuppleInfo info, Model model, RedirectAttributes redirectAttributes){
        
        //获取sessionUser
        User sessionUser = (User) session.getAttribute("sessionUser");
        
        UserResult result = new UserResult();
        
        //输入校验
        UserVerTool.verResume(info.getResume(), result);
        
        //校验通过
        if(result.getResumeError() == null){
            
            try {
                //保存修改
                userService.setResume(info.getResume(), sessionUser.getId());
                
                //修改成功，重定向到页面
                redirectAttributes.addAttribute("msgSuccess", "保存信息成功！！");
                return "redirect:/webnote/user/userInfoEdit_2";
            } catch (UserNullException ex) {
                //用户出现异常，重定向回登录页
                redirectAttributes.addAttribute("msg", "用户信息失效，保存失败，请重新登录。");
                return "redirect:/webnote/main";
            }
        }
        
        //校验不通过或者修改失败，重定向到页面回显，因为redirectAttributes无法传递对象，这里没有采用重定向
        result.setResume(info.getResume());
        model.addAttribute("result", result);
        return "userInfoEdit_resume";
    }
    
    /**
     * 修改用户基本信息
     * @param session
     * @param user
     * @param info
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/updateUserInfo")
    public String updateUserBasicInfo(HttpSession session, User user, UserSuppleInfo info, Model model, RedirectAttributes redirectAttributes){
        //获取sessionUser
        User sessionUser = (User) session.getAttribute("sessionUser");
        
        //校验输入
        UserResult result = new UserResult();
        Boolean verResult = verUserBasicInfo(user, info, result);
        
        //如果校验通过
        if(verResult){
            try {
                //修改信息
                userService.setUserBasicInfo(user, info, sessionUser.getId());
                
                //刷新sessionUser
                try {
                    User newUser = userService.login(sessionUser);
                    session.setAttribute("sessionUser", newUser);
                } catch (PasswordErrorException | UserNullException ex) {
                    //这里的异常正常情况都不会出现，除非正在此时被删除用户或者修改密码，因此直接跳转至登录页以便用户重新登陆
                    redirectAttributes.addAttribute("msg", "用户信息失效，保存失败，请重新登录。");
                    return "redirect:/webnote/main";
                }
                
                //修改成功，重定向到页面
                redirectAttributes.addAttribute("msgSuccess", "修改用户密码成功！！");
                return "redirect:/webnote/user/userInfoEdit";
                
            } catch (UserNullException ex) {
                //用户出现异常，重定向回登录页
                redirectAttributes.addAttribute("msg", "用户信息失效，保存失败，请重新登录。");
                return "redirect:/webnote/main";
            }
        }
        
        //校验失败，重定向到页面回显，因为redirectAttributes无法传递对象，这里没有采用重定向
        result.setNickName(user.getNickName());
        result.setGender(info.getGender());
        result.setBirthday(info.getBirthday());
        result.setNation(info.getNation());
        result.setAddress(info.getAddress());
        model.addAttribute("result", result);
        return "userInfoEdit_basic";
    }

    /*
    * 校验用户基本信息
    */
    private Boolean verUserBasicInfo(User user, UserSuppleInfo info, UserResult result) {
        
        //校验昵称
        UserVerTool.verNickName(user, result);
        
        //校验性别
        UserVerTool.verGender(info.getGender(), result);
        
        //校验生日
        UserVerTool.verBirthday(info.getBirthday(), result);
        
        //校验民族
        UserVerTool.verNation(info.getNation(), result);
        
        //校验所在地
        UserVerTool.verAddress(info.getAddress(), result);
        
        //判断,返回
        if(result.getNickNameError() == null && result.getGenderError() == null
                && result.getBirthdayError() == null && result.getNationError() == null
                && result.getAddressError() == null){
            return true;
        }
        return false;
    }

    
    
}
