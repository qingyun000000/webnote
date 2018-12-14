/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_manager.exception;

/**
 * 异常类：用户已存在/用户重复
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
