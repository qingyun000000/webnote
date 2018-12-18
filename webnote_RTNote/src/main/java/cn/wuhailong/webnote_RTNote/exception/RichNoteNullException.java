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
public class RichNoteNullException extends Exception{

    public RichNoteNullException() {
    }

    public RichNoteNullException(String message) {
        super(message);
    }

    public RichNoteNullException(Throwable cause) {
        super(cause);
    }
}
