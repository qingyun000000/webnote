/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.wuhailong.webnote_note.domain.vo;

/**
 * 笔记模块页面回显类
 * @author Administrator
 */
public class NoteResult {
    private String noteTitle;           //笔记标题
    private String content;             //笔记内容
    private String description;         //描述，图片说明，心情
    private String noteTitleError;          
    private String contentError;            
    private String descriptionError;
    private String imageError;         //图片错误信息

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public String getImageError() {
        return imageError;
    }

    public void setImageError(String imageError) {
        this.imageError = imageError;
    }
    
}
