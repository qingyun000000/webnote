/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_manager.tools;

import cn.wuhailong.webnote_manager.domain.vo.ManagerResult;
import java.util.regex.Pattern;

/**
 * 管理员信息校验工具类
 * @author Administrator
 */
public class ManagerVerTool {
    /**
     * 输入校验——校验密码
     * @param user
     * @param result
     */
    public static void verManagerPassword(String managerPassword, ManagerResult result) {
        //校验密码
        //正则表达式:满足6-20字母或者数字
        boolean matches = Pattern.matches("^[A-Za-z0-9]{6,20}$",managerPassword);
        if(!matches){
            result.setManagerPasswordError("密码需为6-20位字母或数字");
        }
    }
    
    /**
     * 输入校验——校验新密码
     * @param user
     * @param result
     */
    public static void verManagerPasswordNew(String managerPasswordNew, ManagerResult result) {
        //校验密码
        //正则表达式:满足6-20字母或者数字
        boolean matches = Pattern.matches("^[A-Za-z0-9]{6,20}$",managerPasswordNew);
        if(!matches){
            result.setManagerPasswordNewError("密码需为6-20位字母或数字");
        }
    }

    /**
     * 输入校验——校验用户名
     * @param user
     * @param result
     */
    public static void verManagerName(String managerName, ManagerResult result) {
        //校验用户名
        //正则表达式:满足6-20字母或者数字
        boolean matches = Pattern.matches("^[A-Za-z0-9]{6,20}$",managerName);
        if(!matches){
            result.setManagerNameError("账号需为6-20位字母或数字");
        }
    }
}
