/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_note.exception;

/**
 * 异常类：文本笔记为空
 * @author Administrator
 */
public class NoteNullException extends Exception{

    public NoteNullException() {
    }

    public NoteNullException(String message) {
        super(message);
    }

    public NoteNullException(Throwable cause) {
        super(cause);
    }
}
