/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_manager.domain.vo;

/**
 * 管理员类错误信息回显
 * @author Administrator
 */
public class ManagerResult {
    private String resultMessage;
    private String managerNameError;
    private String managerPasswordError;
    private String managerPassword2Error;
    private String managerPasswordNewError;
    private String managerName;
    private String managerPassword;
    private String managerPassword2;
    private String managerPasswordNew;

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getManagerNameError() {
        return managerNameError;
    }

    public void setManagerNameError(String managerNameError) {
        this.managerNameError = managerNameError;
    }

    public String getManagerPasswordError() {
        return managerPasswordError;
    }

    public void setManagerPasswordError(String managerPasswordError) {
        this.managerPasswordError = managerPasswordError;
    }

    public String getManagerPassword2Error() {
        return managerPassword2Error;
    }

    public void setManagerPassword2Error(String managerPassword2Error) {
        this.managerPassword2Error = managerPassword2Error;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    public String getManagerPassword2() {
        return managerPassword2;
    }

    public void setManagerPassword2(String managerPassword2) {
        this.managerPassword2 = managerPassword2;
    }

    public String getManagerPasswordNewError() {
        return managerPasswordNewError;
    }

    public void setManagerPasswordNewError(String managerPasswordNewError) {
        this.managerPasswordNewError = managerPasswordNewError;
    }

    public String getManagerPasswordNew() {
        return managerPasswordNew;
    }

    public void setManagerPasswordNew(String managerPasswordNew) {
        this.managerPasswordNew = managerPasswordNew;
    }

}
