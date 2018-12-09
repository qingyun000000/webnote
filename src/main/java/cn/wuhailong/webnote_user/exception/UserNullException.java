/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_user.exception;

/**
 * 异常类：用户为空
 * @author Administrator
 */
public class UserNullException extends Exception{

    public UserNullException() {
    }

    public UserNullException(String message) {
        super(message);
    }

    public UserNullException(Throwable cause) {
        super(cause);
    }
}
