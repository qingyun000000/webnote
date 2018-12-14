/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_user.exception;

/**
 * 异常类：用户已存在/用户重复
 * @author Administrator
 */
public class UserExistException extends Exception{

    public UserExistException() {
    }

    public UserExistException(String message) {
        super(message);
    }

    public UserExistException(Throwable cause) {
        super(cause);
    }
}
