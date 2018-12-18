/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_RTNote.domain.vo;

/**
 * 富文本模块页面回显类
 * @author Administrator
 */
public class NoteResult {
    private String noteTitle;           //笔记标题
    private String content;             //笔记内容
    private String noteTitleError;    
    private String contentError;

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNoteTitleError() {
        return noteTitleError;
    }

    public void setNoteTitleError(String noteTitleError) {
        this.noteTitleError = noteTitleError;
    }

    public String getContentError() {
        return contentError;
    }

    public void setContentError(String contentError) {
        this.contentError = contentError;
    }
    
}
