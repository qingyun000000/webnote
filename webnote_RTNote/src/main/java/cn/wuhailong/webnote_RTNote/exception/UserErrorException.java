/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_RTNote.exception;

/**
 * 异常类：文本笔记为空
 * @author Administrator
 */
public class UserErrorException extends Exception{

    public UserErrorException() {
    }

    public UserErrorException(String message) {
        super(message);
    }

    public UserErrorException(Throwable cause) {
        super(cause);
    }
}
