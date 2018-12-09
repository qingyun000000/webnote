/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_user.exception;

/**
 * 异常类：密码错误
 * @author Administrator
 */
public class PasswordErrorException extends Exception{

    public PasswordErrorException() {
    }

    public PasswordErrorException(String message) {
        super(message);
    }

    public PasswordErrorException(Throwable cause) {
        super(cause);
    }
}
