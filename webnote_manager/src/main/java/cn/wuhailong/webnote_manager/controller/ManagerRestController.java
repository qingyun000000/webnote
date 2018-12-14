/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_manager.controller;

import cn.wuhailong.webnote_manager.domain.pojo.ManagerListPage;
import cn.wuhailong.webnote_manager.domain.pojo.Manager;
import cn.wuhailong.webnote_manager.exception.ManagerExistException;
import cn.wuhailong.webnote_manager.exception.ManagerNullException;
import cn.wuhailong.webnote_manager.exception.PasswordErrorException;
import cn.wuhailong.webnote_manager.service.ManagerService;
import cn.wuhailong.webnote_manager.tools.ManagerVerTool;
import cn.wuhailong.webnote_manager.domain.vo.ManagerResult;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Administrator
 */
@RestController
@RequestMapping("/webnote/managerRest")
public class ManagerRestController {
    
    @Autowired
    private ManagerService managerService;
    
   
    /**
     * 管理员列表
     * @param session
     * @param page
     * @return
     */
    @RequestMapping("/managerList")
    public ManagerListPage managerList(HttpSession session, ManagerListPage page){
        Manager sessionManager = (Manager) session.getAttribute("sessionManager");
        if("admin".equals(sessionManager.getManagerName())){
            page = managerService.getManagerListPage(page);
            
            //判断是否有当前页，没有(int型默认初始化为0）或者错误，则置于1。
            int showPage = page.getShowPage();
            if(showPage < 1 || showPage > page.getTotalPage()){
                showPage = 1;
                page.setShowPage(showPage);
            }
            List<Manager> managers = managerService.getManagerListByPage(page);
            page.setMangerList(managers);
            return page;
        }else{
            return null;
        }
    }
    
    /**
     * 校验新增时管理员账号是否可用
     * @param session
     * @param manager
     * @return
     */
    @RequestMapping("/verManagerNameAdd")
    public String verManagerNameAdd(HttpSession session,Manager manager){
        Manager sessionManager = (Manager) session.getAttribute("sessionManager");
        if("admin".equals(sessionManager.getManagerName())){
            Boolean result = managerService.VerManagerNameAdd(manager.getManagerName());
            if(result == true){
                return "success";
            }else{
                return "管理员账号重复，请更换";
            }
        }else{
            return "非超级管理员，无法获取信息";
        }
    }
    
    /**
     * 新增管理员
     * @param session
     * @param manager
     * @return
     */
    @RequestMapping("/addManager")
    public ManagerResult addManager(HttpSession session, Manager manager){
        
        //获取session中的用户，判断是否是超级管理员，不是则无权限进行后续操作
        Manager sessionManager = (Manager) session.getAttribute("sessionManager");
        if("admin".equals(sessionManager.getManagerName())){
            
            ManagerResult result = new ManagerResult();
            
            //校验
            Boolean verResult = VerManagerInfoAdd(manager, result);
            
            //校验通过
            if(verResult){
                try {
                    managerService.add(manager);
                    
                    //新增成功，重定向至管理员列表
                    result.setResultMessage("success");
                    return result;
                    
                } catch (ManagerExistException ex) {
                    result.setManagerNameError(ex.getMessage());
                }
            }
            
            //校验不通过，或者新增不成功，
            result.setResultMessage("新增用户失败");
            return result;
            
        }else{
            //直接返回错误信息
            ManagerResult result = new ManagerResult();
            result.setResultMessage("非超级管理员，不能新增用户！！");
            return result;
        }
    }
    
    /*
    * 新增管理员时的输入校验方法
    */
    private Boolean VerManagerInfoAdd(Manager manager, ManagerResult result) {
        //校验账户
        String managerName = manager.getManagerName();
        ManagerVerTool.verManagerName(managerName, result);
        
        //校验密码
        String managerPassword = manager.getManagerPassword();
        ManagerVerTool.verManagerPassword(managerPassword, result);
        
        //校验重复密码
        if(manager.getManagerPassword2() == null || !manager.getManagerPassword2().equals(managerPassword)){
            result.setManagerPassword2Error("两次输入密码不一致");
        }
        
        //判断校验是否通过，返回
        if(result.getManagerNameError() == null && result.getManagerPasswordError() == null && result.getManagerPassword2Error() == null){
            return true;
        }
        return false;
    }
    
    @RequestMapping("/deleteManager")
    public String deleteManager(HttpSession session, Manager manager){
        //获取session中的用户，判断是否是超级管理员，不是则无权限进行后续操作，直接重定向至管理主页
        Manager sessionManager = (Manager) session.getAttribute("sessionManager");
        if("admin".equals(sessionManager.getManagerName())){
            try {
                String managerName = managerService.delete(manager);
                
                return "成功删除管理员:  " + managerName +"  ！！";
            } catch (Exception ex) {
                return "删除管理员失败！！" + ex.getMessage();
            }
        }else{
            return "非超级管理员，不具有删除管理员账户权限！！";
        }
    }
    
    @RequestMapping("/verManagerPasswordUpdate")
    public String verManagerPasswordUpdate(HttpSession session, Manager manager){
        Manager sessionManager = (Manager) session.getAttribute("sessionManager");
        
        Boolean result = managerService.verPassword(sessionManager.getManagerId(), manager.getManagerPassword());
        if(result){
            return "success";
        }
        return "原密码错误";
    }
    
    @RequestMapping("/updatePassword")
    public ManagerResult updatePassword(HttpSession session, Manager manager){
        
        Manager sessionManager = (Manager) session.getAttribute("sessionManager");
        
        ManagerResult result = new ManagerResult();

        //校验
        Boolean verResult = VerManagerInfoUpdatePassword(manager, result);

        //校验通过
        if(verResult){
            try {
                String managerName = managerService.updatePassword(sessionManager.getManagerId(), manager.getManagerPassword(), manager.getManagerPasswordNew());
                //新增成功，重定向至管理员列表
                result.setResultMessage("success");
                return result;
            } catch (ManagerNullException ex) {
                result.setResultMessage("修改密码失败，账号不存在");
            } catch (PasswordErrorException ex) {
                result.setManagerPasswordError(ex.getMessage());
            }

        }

        //校验不通过，或者新增不成功，
        result.setResultMessage("修改密码失败");
        return result;
    }
    
    /*
    * 修改密码时的输入校验方法
    */
    private Boolean VerManagerInfoUpdatePassword(Manager manager, ManagerResult result) {
        //校验原密码
        //校验密码
        String managerPassword = manager.getManagerPassword();
        ManagerVerTool.verManagerPassword(managerPassword, result);
        
        //校验新密码
        String managerPasswordNew = manager.getManagerPasswordNew();
        ManagerVerTool.verManagerPasswordNew(managerPassword, result);
        
        //校验重复密码
        if(manager.getManagerPassword2() == null || !manager.getManagerPassword2().equals(managerPasswordNew)){
            result.setManagerPassword2Error("两次输入密码不一致");
        }
        
        //判断校验是否通过，返回
        if(result.getManagerPasswordNew() == null && result.getManagerPasswordError() == null && result.getManagerPassword2Error() == null){
            return true;
        }
        return false;
    }
}
