/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_note.exception;

/**
 * 异常类：心情图片为空
 * @author Administrator
 */
public class ImageNoteNullException extends Exception{

    public ImageNoteNullException() {
    }

    public ImageNoteNullException(String message) {
        super(message);
    }

    public ImageNoteNullException(Throwable cause) {
        super(cause);
    }
}
